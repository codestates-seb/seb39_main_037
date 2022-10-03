package com.main.project.securityConfig;

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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream test = request.getInputStream();//리뷰를 쓴 아이디 확인
            String messageBody = StreamUtils.copyToString(test, UTF_8);
            ReviewPostDto reviewPostDto = objectMapper.readValue(messageBody, ReviewPostDto.class);

            if (reviewPostDto.getUserId()!=Long.parseLong(userId)) {
                response.getOutputStream().println("NOT AUTHORIZE!!");
                return false;
            }
        }
        else if(httpMethod.equals("PATCH")){
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream test = request.getInputStream();//리뷰를 쓴 아읻 확인
//            String line = "";
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(test));
//            StringBuilder stringBuilder = new StringBuilder();
            String messageBody = StreamUtils.copyToString(test, UTF_8);
            ReviewPatchDto reviewPatchDto = objectMapper.readValue(messageBody, ReviewPatchDto.class);

            Review review = reviewService.findReview(reviewPatchDto.getReviewId());

           if(review.getWebUser().getUserId()!=Long.parseLong(userId)){
               response.getOutputStream().println("작성자만 수정 할 수 있습니다.");
               return false;
            }

        } else if (httpMethod.equals("DELETE")) {
           long toDeleteId  = Long.parseLong(request.getParameter("review-id"));
            Review review = reviewService.findReview(toDeleteId);

            if(review.getWebUser().getUserId()!=toDeleteId){
                response.getOutputStream().println("작성자만 삭제할 수 있습니다.");
                return false;
            }
        }

        return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}