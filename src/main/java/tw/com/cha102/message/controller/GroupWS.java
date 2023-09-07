package tw.com.cha102.message.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Tuple;
import tw.com.cha102.message.model.ChatVO;
import tw.com.cha102.message.model.MessageVO;
import tw.com.cha102.message.service.JedisHandleMessage;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ServerEndpoint("/GroupWS/{groupId}")
@Component
public class GroupWS {
    private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

    Gson gson = new Gson();
    @OnOpen
    public void onOpen(@PathParam("groupId") String userName, Session userSession) throws IOException {
        connectedSessions.add(userSession);
        String text = String.format("Session ID = %s, connected; groupId = %s", userSession.getId(), userName);
        System.out.println(text);
    }

    @OnMessage
    public void onMessage(Session userSession, String message) {
        ChatVO groupMessage = gson.fromJson(message, ChatVO.class);
        String sender = groupMessage.getSender();
        String groupId = groupMessage.getGroupId();
        Integer readNum = groupMessage.getReadNum();
        String time = groupMessage.getChatTime();
        if ("history".equals(groupMessage.getType())){
            Set<Tuple> histroyData = JedisHandleMessage.getgroupHistoryMsg(groupId);
            String historyMsg = gson.toJson(histroyData);
            ChatVO cmHistory = new ChatVO("history",sender,groupId,historyMsg,readNum,time);
            if (userSession != null && userSession.isOpen()){
                userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
                System.out.println("history="+gson.toJson(cmHistory));
                return ;
            }
        }
        JedisHandleMessage.saveGroupMessage(groupId,sender,message);
        for (Session session : connectedSessions) {
            if (session.isOpen())
                session.getAsyncRemote().sendText(message);

        }
        System.out.println("Message received: " + message);
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        connectedSessions.remove(userSession);
        String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
                userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
        System.out.println(text);
    }

    @OnError
    public void onError(Session userSession, Throwable e) {
        System.out.println("Error: " + e.toString());
    }


}
