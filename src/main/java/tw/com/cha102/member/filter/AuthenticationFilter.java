package tw.com.cha102.member.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationFilter extends OncePerRequestFilter {
    private final Set<String> allowedUrls = new HashSet<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestUrl = request.getRequestURI();
        if (allowedUrls.contains(requestUrl)){
            filterChain.doFilter(request, response);
        } else {

        }
    }
}
