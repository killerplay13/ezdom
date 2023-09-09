package tw.com.cha102.member.filterConfig;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tw.com.cha102.member.filter.AuthenticationFilter;

import javax.servlet.FilterRegistration;

@Configuration
public class FilterConfig {
    @Autowired
    AuthenticationFilter authenticationFilter;
    @Bean
    public FilterRegistrationBean filterRegistration(){
        FilterRegistrationBean<AuthenticationFilter> bean =new FilterRegistrationBean<>();
        bean.setFilter(authenticationFilter);
        bean.addUrlPatterns("/frontend/*");
        bean.setOrder(10);
        bean.setName("authenticationFilter");
        bean.setEnabled(false);
        return bean;
    }
}
