package com.main.project.user.repository.service;

import com.main.project.badge.entity.UserBadge;
import com.main.project.badge.entity.Badge;
import com.main.project.badge.service.BadgeServiceImpl;
import com.main.project.comment.entity.Comment;
import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.review.entity.Review;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.user.dto.UserDto;
import com.main.project.user.entity.WebUser;
import com.main.project.user.entity.WithdrawalUser;
import com.main.project.user.repository.UserRepository;
import com.main.project.user.repository.WithdrawalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServieImpl implements  UserService {

        private BadgeServiceImpl badgeService;
        UserRepository userRepository;
        WithdrawalUserRepository withdrawalUserRepository;
        @Autowired
        BCryptPasswordEncoder bCryptPasswordEncoder;

        public UserServieImpl(BadgeServiceImpl badgeService, UserRepository userRepository, WithdrawalUserRepository withdrawalUserRepository) {
                this.badgeService = badgeService;
                this.userRepository = userRepository;
                this.withdrawalUserRepository = withdrawalUserRepository;
        }


        @Override
        public WebUser registerUser(WebUser newUser) {

                newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
                //  newUser.getPassword()를 BCryptPasswordEncoder.encode()로 암호화
                WebUser newpostedUser = userRepository.save(newUser);
                //중복 체크(닉네임, 이메일)
                return newpostedUser;
        }

        @Override
        public WebUser editUser(WebUser editUser) {
                //이메일로 유저가 있는지 체크
                WebUser webUser = getWebUser(editUser);
                // 새로운 webUser 객체 생성
                Optional.ofNullable(editUser.getNickName())
                        .ifPresent(webUser::setNickName);
                Optional.ofNullable(editUser.getUserName())
                        .ifPresent(webUser::setUserName);
                Optional.ofNullable(editUser.getEmail())
                        .ifPresent(webUser::setEmail);

                return userRepository.save(webUser);
        }

        private WebUser getWebUser(WebUser editUser) {
                return userRepository.findByEmail(editUser.getEmail()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
        }


        @Override
        public WebUser editUserPassWord(UserDto.patchUserpasswordDto patchUserpasswordDto) {

                WebUser edittingUser = checkUserByUserId(patchUserpasswordDto.getUserId());

                //비밀번호가 맞는지 체크
                if (isPassWordMatch(patchUserpasswordDto.getOldPassWord(), edittingUser)) {
                        edittingUser.setPassword(bCryptPasswordEncoder.encode(patchUserpasswordDto.getNewPassword()));
                        userRepository.save(edittingUser);
                } else {
                        throw new BusinessLogicException(ExceptionCode.PASSWORD_NOT_MATCH);
                }
                return edittingUser;
        }


        @Override//UserId로 DB의 유저 확인
        public WebUser checkUserByUserId(long userid) {
                return userRepository.findById(userid).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
        }

        @Override
        public UserDto.responseUserActivityDto findMyUserActivity(long userId) {
                WebUser webUser = checkUserByUserId(userId);

                List<Comment> ListOfComment = webUser.getComments();
                List<Review> ListOfReview = webUser.getReviews();
                List<ThumbUp> ListOfThumbUp = webUser.getThumbUps();
                List<UserBadge> ListOfBadge = webUser.getUserBadges();
                List<UserDto.responseBadgeDto> responseBadgeDtos = ListOfBadge.stream()
                        .map(userbadge -> new UserDto.responseBadgeDto(userbadge.getBadge().getBadgeId(), userbadge.getBadge().getBadgeName(),
                                userbadge.getBadge().getDescription(), uriMaker(userbadge.getBadge(), "badge"))).collect(Collectors.toList());

                UserDto.responseUserActivityDto responseUserActivityDto = new UserDto.responseUserActivityDto();
                responseUserActivityDto.setListOfComment(ListOfComment);
                responseUserActivityDto.setListOfReview(ListOfReview);
                responseUserActivityDto.setListOfThumbUp(ListOfThumbUp);
                responseUserActivityDto.setListOfBadge(responseBadgeDtos);
                return responseUserActivityDto;
        }

        @Override
        public void deActiveUser(long userid, String password) {
                WebUser deActiveUser = checkUserByUserId(userid);

                checkIsUserDeActive(deActiveUser);

                if (isPassWordMatch(password, deActiveUser)) {
                        deActiveUser.setIsUserActive(WebUser.UserActive.Withdrawal);//유저 상태 휴면으로 변경 -> 리팩토링 필요할 듯,
                        WithdrawalUser withdrawalUser = new WithdrawalUser();
                        withdrawalUser.setWebUser(deActiveUser);
                        withdrawalUserRepository.save(withdrawalUser);
                } else {
                        throw new BusinessLogicException(ExceptionCode.PASSWORD_NOT_MATCH);
                }

        }


        @Override
        public Page<WebUser> findAllUser(int page) {

                return userRepository.findAll(PageRequest.of(page, 10, Sort.by("userId").descending()));
        }

        @Override
        public List<WebUser> findUserBydate(LocalDate start, LocalDate end) {
                return userRepository.findAllByCreatedAtBetween(start, end)
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
        }

        @Override
        public WebUser findUserByEmail(String email) {
                return userRepository.findByEmail(email)
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));

        }

        public void assignBadge(long userID) {//첫 리뷰작성 후 기념 뱃지 제공

                WebUser foundUser = this.checkUserByUserId(userID);
                int howManyReviews = foundUser.getReviews().size();

                if (howManyReviews == 1) {
                        Badge firstReviewBadge = badgeService.findBadge(1);
                        new UserBadge().addUserAndBage(foundUser, firstReviewBadge);
                        userRepository.save(foundUser);//첫리뷰 배지를 받은 유저를 저장
                }

        }

        @Override
        public WebUser findUserByFileName(String filename) {
                return userRepository.findByprofileImgName(filename).orElseThrow(() -> new BusinessLogicException(ExceptionCode.FILE_IS_NOT_EXIST));
        }

        @Override
        public boolean isPassWordMatch(String dtoPassWord, WebUser toCheckUser) {
                return bCryptPasswordEncoder.matches(dtoPassWord, toCheckUser.getPassword());
        }

        @Override
        public void checkIsUserDeActive(WebUser deActiveUser) {
                if (deActiveUser.getIsUserActive() == WebUser.UserActive.Withdrawal) {
                        throw new BusinessLogicException(ExceptionCode.ALREADY_DEACTICATED_USER);
                }
        }

        public String uriMaker(Badge badge, String path) {

                return ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/" + path + "/download/")
                        .path(String.valueOf(badge.getBadgeName()))
                        .toUriString();
        }

        public WebUser addProfileImg(long userid, MultipartFile profileImg) throws IOException {

                WebUser webUser = userRepository.findById(userid).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
                webUser.setProfileImg(profileImg.getBytes());
                webUser.setProfileImgName(profileImg.getOriginalFilename());
                return userRepository.save(webUser);

        }
}


