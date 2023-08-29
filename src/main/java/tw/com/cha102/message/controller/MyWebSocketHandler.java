package tw.com.cha102.message.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 当 WebSocket 连接建立时触发
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 当 WebSocket 连接关闭时触发
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
