package tw.com.cha102.employee.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tw.com.cha102.member.filter.AuthenticationFilter;
@Configuration
public class EmpFilterConfig {

    @Autowired
    EmpAuthenticationFilter empAuthenticationFilter;
    @Bean
    public FilterRegistrationBean filterRegistrationBackend(){
        FilterRegistrationBean<EmpAuthenticationFilter> bean =new FilterRegistrationBean<>();
        bean.setFilter(empAuthenticationFilter);
        bean.addUrlPatterns("/backend/*");
        bean.setOrder(10);
        bean.setName("empAuthenticationFilter");
        bean.setEnabled(true);
        return bean;
    }
}
