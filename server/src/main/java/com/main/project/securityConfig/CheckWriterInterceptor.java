import com.main.project.review.service.ReviewService;
import com.main.project.user.repository.service.UserService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckWriterInterceptor implements HandlerInterceptor {

    private ReviewService userService;

    public CheckWriterInterceptor(ReviewService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String str1 = request.getParameter("userId");
        int content_idx = Integer.parseInt(str1);



        request.getHeader("Authorization");



        ContentBean currentContentBean = boardService.getContent(content_idx);

        if(loginUserBean.getUser_idx()!= currentContentBean.getContent_writer_idx()) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/board/not_writer");
            return false;
        }

        return true;
    }
}