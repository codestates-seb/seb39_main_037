package com.main.project.securityConfig.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.project.review.dto.ReviewPatchDto;
import com.main.project.review.dto.ReviewPostDto;
import com.main.project.review.entity.Review;
import com.main.project.review.service.ReviewService;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static java.nio.charset.StandardCharsets.*;

@Component
public class CheckReviewWriterInterceptor implements HandlerInterceptor {

    private ReviewService reviewService;


    public CheckReviewWriterInterceptor(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String httpMethod = request.getMethod();
        String jwtHeader = request.getHeader("Authorization");
        String jwtToken = jwtHeader.replace("Bearer ", "");

        String userId = JWT.require(Algorithm.HMAC512("seb29_main37 jwt token")).build().verify(jwtToken).getClaim("id").toString();

        if (httpMethod.equals("POST")) {//post일 경우 json userId와 토큰 id를 체크한다.

            Map pathvariable  = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

            long toAddId = Long.parseLong((String) pathvariable.get("user-id"));



            if (toAddId!=Long.parseLong(userId)) {
                response.setContentType("text/html; charset=UTF-8"); // 보낼 때 한글 인코딩
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println("인증정보가 올바르지 않습니다!!");
                return false;
            }
        }
        else if(httpMethod.equals("PATCH")){
            Map pathvariable  = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

            long toAddId = Long.parseLong((String) pathvariable.get("review-id"));
            Review review = reviewService.findReview(toAddId);

           if(review.getWebUser().getUserId()!=Long.parseLong(userId)){
               response.setContentType("text/html; charset=UTF-8");
               response.setCharacterEncoding("UTF-8");
               response.getWriter().println("작성자만 수정 할 수 있습니다.");
               return false;
            }

        } else if (httpMethod.equals("DELETE")) {
            Map pathvariable  = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

            long toDeleteId = Long.parseLong((String) pathvariable.get("review-id"));
            Review review = reviewService.findReview(toDeleteId);

            if(review.getWebUser().getUserId()!=Long.parseLong(userId)){
                response.setContentType("text/html; charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println("작성자만 삭제할 수 있습니다.");
                return false;
            }
        }

        return true;

    }

}