package tw.com.cha102.employee.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
@Component
public class EmpAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        HttpSession session = request.getSession();
        if (session.getAttribute("employeeId") == null) {
//            response.sendRedirect(request.getContextPath() + "/ezdom/backendemp/empSignin.html");
            System.out.println("LonginFilter:使用者不為登入狀態，sessionid未被授權");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else {
            filterChain.doFilter(request, response);
        }


    }
}