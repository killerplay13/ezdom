package tw.com.cha102.member.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestUrl = request.getRequestURI();

        // 允許特定 URL 通過Filter
        if ("/login".equals(requestUrl) || "/signup".equals(requestUrl) || "/ezdomindex".equals(requestUrl)) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("/login"); // 若沒有有效的 session 或未登入，則重新導向到登入頁面
            return;
        }
        // 若已登入，則繼續處理請求
        filterChain.doFilter(request, response);
    }
}
