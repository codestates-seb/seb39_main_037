package com.main.project.user.service;

import com.main.project.badge.entity.Badge;
import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.user.entity.WebUser;
import com.main.project.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServieImpl implements  UserService{

        UserRepository userRepository;

        public UserServieImpl(UserRepository userRepository) {
                this.userRepository = userRepository;
        }


        @Override
        public WebUser registerUser() {
                return null;
        }

        @Override
        public WebUser editUser() {
                return null;
        }

        @Override
        public Page<WebUser> findAllUser() {
                return null;
        }

        @Override
        public WebUser registerUser(WebUser newUser) {


        //BCryptPasswordEncoder 로 비밀번호 가져와 암호화 저장
        //  newUser.getPassword()를 BCryptPasswordEncoder.encode()로 암호화
        //중복 체크(닉네임, 이메일)
                return null;
        }

        @Override
        public WebUser editUser(WebUser editUser) {
        // editUser.getOldPassword == DB password인지를 체크
        // 새로운 webUser 객체 생성
        // save()
//                userRepository.findAll(PageRequest.of(1,10, Sort.by("userId").descending())
                return null;
        }

        @Override
        public Page<WebUser> findAllUser(int page, int size, String sortby /* 정렬기준 */) {

                return userRepository.findAll(PageRequest.of(page, size, Sort.by(sortby).descending()));
        }

        @Override
        public WebUser findUser(long user) {
                return null;
        }

        @Override
        public WebUser inActiveUser() {
                return null;
        }

        @Override
        public Page<WebUser> findUsersByLevel() {
                return null;
        }

        @Override
        public List<Badge> findUsersBadge() {
                return null;
        }

        @Override
        public List<ThumbUp> findUsersLikes() {
                return null;
        }

        @Override
        public WebUser findAllUserByMonth() {
                return null;
        }

        @Override
        public WebUser findAllUserByYear() {
                return null;
        }

        @Override
        public WebUser findNewUsersByMonth() {
                return null;
        }

        @Override
        public WebUser findNewUsersByYear() {
                return null;
        }

        @Override
        public WebUser findUserByEmailForAuth(String email) {
                Optional<WebUser> checkedUser = userRepository.findByEmail(email);
                WebUser foundUser = checkedUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_IS_NOT_EXIST));

                return foundUser;
        }
}
