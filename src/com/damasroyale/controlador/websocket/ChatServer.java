package com.damasroyale.controlador.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/room/{roomnumber}")
public class ChatServer {

	private static Map<String, Session> openSessions = new HashMap<String, Session>();

	@OnOpen
	public void onConnectionOpen(Session session, @PathParam("roomnumber") String roomnumber) {

		session.getUserProperties().put("roomnumber", roomnumber);
		openSessions.put(String.valueOf(session.getId()), session);
	}

	@OnMessage
	public void message(String message, Session client, @PathParam("roomnumber") String roomnumber) {

		for (Map.Entry<String, Session> entry : openSessions.entrySet()) {

			Session s = entry.getValue();

			System.out.println(s);

			if (s.isOpen() && s.getUserProperties().get("roomnumber").equals(roomnumber)) {
				try {
					s.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@OnClose
	public void onConnectionClose(Session session) {
		openSessions.remove(session.getId());
	}

}
