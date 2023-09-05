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
        String contextPath = request.getContextPath();
        String requestUrl = request.getRequestURI().replace(contextPath, "");

        // 允許特定 URL 通過Filter
        if ("/frontendmember/empSignin.html".equals(requestUrl) || "/frontendmember/account-signup.html".equals(requestUrl)) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/frontendmember/empSignin.html");
            return;
        }

        // 若已登入，則繼續處理請求
        filterChain.doFilter(request, response);
    }
}