package kr.or.connect.springwebsocket.utils;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class MyWebSocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> accessClients = new ArrayList<>();

    /**
     * 웹 소켓 연결 이벤트
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connection opened: " + session);

        //접속한 유저의 세션을 저장
        accessClients.add(session);
    }

    /**
     * 메시지 핸들러
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Message: " + session + ", message: " + message);

        String sender = session.getId();

        //모든 유저에게 메시지 전달(브로드캐스팅)
        for (WebSocketSession accessClient : accessClients) {
            accessClient.sendMessage(new TextMessage(sender + "`s message: " + message.getPayload()));
        }
    }

    /**
     * 웹 소켓 연결 해제 이벤트
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection closed: " + session + ", status: " + status);

        accessClients.remove(session); //소켓 세션 삭제(새로고침 후 완전히 연결이 해제되버리는 이슈 해결)
    }

    /**
     * Handle an error from the underlying WebSocket message transport.
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println(session + ", Error: " + exception.getMessage());
    }
}
