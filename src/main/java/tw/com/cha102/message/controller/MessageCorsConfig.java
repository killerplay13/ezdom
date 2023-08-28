package tw.com.cha102.message.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@CrossOrigin
public class MessageCorsConfig implements WebSocketConfigurer {
    @Bean(name = "messageCorsConfigurer")
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:5500") // 允许访问的域
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的HTTP方法
                        .allowedHeaders("Content-Type"); // 允许的HTTP头
            }
        };
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebSocketHandler(), "/websocket")
                .setAllowedOrigins("http://127.0.0.1:5500")
                .withSockJS(); // 如果需要支持SockJS
    }
}
