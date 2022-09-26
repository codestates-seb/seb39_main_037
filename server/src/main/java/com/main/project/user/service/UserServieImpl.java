package com.main.project.user.service;

import com.main.project.badge.UserBadge;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServieImpl implements  UserService{


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
                WebUser webUser = userRepository.findByEmail(editUser.getEmail()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
                // 새로운 webUser 객체 생성
                Optional.ofNullable(editUser.getNickName())
                        .ifPresent(webUser::setNickName);
                Optional.ofNullable(editUser.getUserName())
                        .ifPresent(webUser::setUserName);
                Optional.ofNullable(editUser.getEmail())
                        .ifPresent(webUser::setEmail);

                WebUser eddittedUser = userRepository.save(webUser);
//                userRepository.findAll(PageRequest.of(1,10, Sort.by("userId").descending())
                return eddittedUser;
        }


        @Override
        public WebUser editUserPassWord(UserDto.patchUserpasswordDto patchUserpasswordDto) {
                WebUser edittingUser =  userRepository.findById(patchUserpasswordDto.getUserId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
                //비밀번호가 맞는지 체크
                if(bCryptPasswordEncoder.matches(patchUserpasswordDto.getOldPassWord(), edittingUser.getPassword())) {
                        edittingUser.setPassword(patchUserpasswordDto.getNewPassword()) ;
                }
                else{
                        new BusinessLogicException(ExceptionCode.PASSWORD_NOT_MATCH);
                }
                return edittingUser;
        }

        @Override
        public WebUser findUser(long userid) {
                return userRepository.findByUserId(userid).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
        }




        public UserDto.responseUserActivityDto findMyUserActivity(UserDto.getMyUserActivityDetailsDto myUserDto) {
                WebUser webUser = userRepository.findByUserId(myUserDto.getUserId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
                List<Comment> ListOfComment = webUser.getComments();
                List<Review> ListOfReview =  webUser.getReviews();
                List<ThumbUp> ListOfThumbUp = webUser.getThumbUps();
                List<UserBadge> ListOfBadge = webUser.getUserBadges();

                return new UserDto.responseUserActivityDto(ListOfComment, ListOfReview, ListOfThumbUp,ListOfBadge);
        }

        @Override
        public void deActiveUser(long userid, String password) {
                WebUser deActiveUser =  userRepository.findById(userid).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
                if(bCryptPasswordEncoder.matches(password, deActiveUser.getPassword())) {
                        deActiveUser.setIsUserActive(WebUser.UserActive.Withdrawal);//유저 상태 휴면으로 변경 -> 리팩토링 필요할 듯,
                        WithdrawalUser withdrawalUser = new WithdrawalUser();
                        withdrawalUser.setWebUser(deActiveUser);
                        withdrawalUserRepository.save(withdrawalUser);

                }
                else{
                        new BusinessLogicException(ExceptionCode.PASSWORD_NOT_MATCH);
                }

        }

        @Override
        public Page<WebUser> findAllUser(int page) {

                return userRepository.findAll(PageRequest.of(page, 10, Sort.by("userId").descending()));
        }

        @Override
        public List<WebUser> findUserBydate(LocalDate start, LocalDate end) {
              List<WebUser> dateFilteredUsers = userRepository.findAllByCreatedAtBetween(start, end).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
                return dateFilteredUsers;
        }

        @Override
        public WebUser findUserByEmailForAuth(String email) {
                Optional<WebUser> checkedUser = userRepository.findByEmail(email);
                WebUser foundUser = checkedUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_IS_NOT_EXIST));

                return foundUser;
        }

        public void assignBadge(long userID) {//첫 리뷰작성 후 기념 뱃지 제공

                WebUser foundUser = findUser(userID);
                int howManyReviews = foundUser.getReviews().size();

                if (howManyReviews == 1) {
                        Badge firstReviewBadge = badgeService.findBadge(1);
                        new UserBadge().addUserAndBage(foundUser, firstReviewBadge);
                        userRepository.save(foundUser);//첫리뷰 배지를 받은 유저를 저장
                }

        }

    public WebUser findUserByFileName(String filename) {

           return    userRepository.findByprofileImgName(filename).get();

    }
}
