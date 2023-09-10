package tw.com.cha102.support.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import tw.com.cha102.support.model.dto.ChatMessage;
import tw.com.cha102.support.model.dto.State;
import tw.com.cha102.message.service.JedisHandleMessage;

@ServerEndpoint("/frontend/SupportWS/{userId}")
@Component
public class SupportWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userId") String userId, Session userSession) throws IOException {
		sessionsMap.put(userId, userSession);
		Set<String> userIds = sessionsMap.keySet();
		State stateMessage = new State("open", userId, userIds);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("Session ID = %s, connected; userId = %s%nusers: %s", userSession.getId(),
				userId, userIds);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession,String message){
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
//		boolean read =  chatMessage.isMessageStatus();
		String time = chatMessage.getMessageTime();

		if (receiver == null) {
			String errorMessage = "Receiver ID is invalid";
//			ChatMessage errorResponse = new ChatMessage("error", sender, receiver, errorMessage, read, time);
			ChatMessage errorResponse = new ChatMessage("error", sender, receiver, errorMessage, time);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(errorResponse));
			}
			return;
		}

		if ("history".equals(chatMessage.getType())){
			List<String> histroyData = tw.com.cha102.message.service.JedisHandleMessage.getHistoryMsg(sender,receiver);
			String historyMsg = gson.toJson(histroyData);
//			ChatMessage cmHistory = new ChatMessage("history",sender,receiver,historyMsg,read,time);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg, time);
			if (userSession != null && userSession.isOpen()){
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				System.out.println("history="+gson.toJson(cmHistory));
				return;
			}
		}
		// 測試
		if ("refresh".equals(chatMessage.getType())){
			List<String> histroyData = tw.com.cha102.message.service.JedisHandleMessage.getHistoryMsg(sender,receiver);
			String historyMsg = gson.toJson(histroyData);
//			ChatMessage cmHistory = new ChatMessage("history",sender,receiver,historyMsg,read,time);
			ChatMessage cmHistory = new ChatMessage("refresh", sender, receiver, historyMsg, time);
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
				userNameClose = userName;
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

