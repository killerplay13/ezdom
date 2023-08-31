package tw.com.cha102.message.controller;


import com.google.gson.Gson;
import org.aspectj.weaver.patterns.Pointcut;
import org.springframework.stereotype.Component;
import tw.com.cha102.message.model.MessageVO;
import tw.com.cha102.message.model.State;
import tw.com.cha102.message.service.JedisHandleMessage;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/FriendWS/{username}")
@Component
public class FriendWS {
    private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
    Gson gson = new Gson();

    @OnOpen
    public void onOpen(@PathParam("username") String userName, Session userSession) throws IOException {
        sessionsMap.put(userName, userSession);
        Set<String> userNames = sessionsMap.keySet();
        State stateMessage = new State("open", userName, userNames);
        String stateMessageJson = gson.toJson(stateMessage);
        Collection<Session> sessions = sessionsMap.values();
        for (Session session : sessions) {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(stateMessageJson);
            }
        }

        String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
                userName, userNames);
        System.out.println(text);
    }

    @OnMessage
    public void onMessage(Session userSession,String message){
        MessageVO chatMessage = gson.fromJson(message, MessageVO.class);
        String sender = chatMessage.getMemberIdA();
        String receiver = chatMessage.getMemberIdB();
        boolean read =  chatMessage.isMessageStatus();
        String time = chatMessage.getMessageTime();

        if (receiver == null) {
            String errorMessage = "Receiver ID is invalid";
            MessageVO errorResponse = new MessageVO("error", sender, receiver, errorMessage, read, time);
            if (userSession != null && userSession.isOpen()) {
                userSession.getAsyncRemote().sendText(gson.toJson(errorResponse));
            }
            return;
        }

        if ("history".equals(chatMessage.getType())){
            List<String> histroyData = JedisHandleMessage.getHistoryMsg(sender,receiver);
            String historyMsg = gson.toJson(histroyData);
            MessageVO cmHistory = new MessageVO("history",sender,receiver,historyMsg,read,time);
            if (userSession != null && userSession.isOpen()){
                userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
                System.out.println("history="+gson.toJson(cmHistory));
                return;
            }
        }
    Session receiverSession = sessionsMap.get(receiver);

        if (receiverSession != null && receiverSession.isOpen()){
            receiverSession.getAsyncRemote().sendText(message);
            userSession.getAsyncRemote().sendText(message);
            JedisHandleMessage.saveChatMessage(sender,receiver,message);
        }
        System.out.println("Message received: " + message );
    }

    @OnError
    public void onError(Session userSession, Throwable e) {
        System.out.println("Error: " + e.toString());
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason){
        String userNameClose =null;
        Set<String> userNames = sessionsMap.keySet();
        for (String userName : userNames) {
            if (sessionsMap.get(userName).equals(userSession)){
                userNameClose =userName;
                sessionsMap.remove(userName);
                break;
            }
        }

        if(userNameClose != null){
            State stateMessage = new State("close", userNameClose, userNames);
            String stateMessageJson = gson.toJson(stateMessage);
            Collection<Session> sessions = sessionsMap.values();
            for (Session session : sessions){
                session.getAsyncRemote().sendText(stateMessageJson);
            }
        }

        String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
                reason.getCloseCode().getCode(), userNames);
        System.out.println(text);
    }

}
