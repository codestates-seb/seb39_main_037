package com.main.project.securityConfig.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.project.comment.dto.CommentPatchDto;
import com.main.project.comment.dto.CommentPostDto;
import com.main.project.comment.entity.Comment;
import com.main.project.comment.service.CommentService;
import com.main.project.comment.service.CommentServiceImpl;
import com.main.project.review.dto.ReviewPatchDto;
import com.main.project.review.dto.ReviewPostDto;
import com.main.project.review.entity.Review;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class CheckCommentWriterInterceptor implements HandlerInterceptor {

    CommentServiceImpl commentServiceImpl;

    public CheckCommentWriterInterceptor(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String httpMethod = request.getMethod();
        String jwtHeader = request.getHeader("Authorization");
        String jwtToken = jwtHeader.replace("Bearer ", "");

        String userId = JWT.require(Algorithm.HMAC512("seb29_main37 jwt token")).build().verify(jwtToken).getClaim("id").toString();


        if (httpMethod.equals("POST")) {//post일 경우 json userId와 토큰 id를 체크한다.

            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream test = request.getInputStream();//리뷰를 쓴 아이디 확인
            String messageBody = StreamUtils.copyToString(test, UTF_8);
            CommentPostDto commentPostDto = objectMapper.readValue(messageBody, CommentPostDto.class);

            if (commentPostDto.getUserId()!=Long.parseLong(userId)) {
                response.getOutputStream().println("인증정보가 올바르지 않습니다!!");
                return false;
            }
        }
        else if(httpMethod.equals("PATCH")){
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream test = request.getInputStream();//리뷰를 쓴 아읻 확인
            String messageBody = StreamUtils.copyToString(test, UTF_8);
            CommentPatchDto commentPatchDto = objectMapper.readValue(messageBody, CommentPatchDto.class);

            long commentId = commentPatchDto.getCommentId();
            Comment comment = commentServiceImpl.findComment(commentId);

            if(comment.getWebUser().getUserId()!=Long.parseLong(userId)){
                response.getOutputStream().println("댓글 작성자만 수정 할 수 있습니다.");
                return false;
            }

        } else if (httpMethod.equals("DELETE")) {
            Map pathvariable  = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            long toDeleteId = (long) pathvariable.get("comment-id");
            Comment comment = commentServiceImpl.findComment(toDeleteId);

            if(comment.getWebUser().getUserId()!=Long.parseLong(userId)){
                response.getOutputStream().println("댓글 작성자만 삭제할 수 있습니다.");
                return false;
            }
        }

        return true;




    }
}
