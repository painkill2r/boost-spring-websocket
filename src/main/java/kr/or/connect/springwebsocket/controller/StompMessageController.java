package kr.or.connect.springwebsocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller("/sock/message")
public class StompMessageController {

    /**
     * /sock/message/send로 요청을 받고,
     * /topic/message로 메시지를 처리
     *
     * @param message
     * @return
     * @MessageMapping STOMP Client의 send에 대한 Target URL
     * @SendTo STOMP Client의 Subscribe에 대한 Target URL(지정되지 않은 경우, @MessageMapping에 지정된 URL + @Configuration에서 설정된 Subscribe prefix URL을 이용)
     * @SubscribeEvent client에서 Subscribe 할 수 있는 URL을 지정(특정 message가 발생하거나 event가 발생했을 때, Client에 값을 전송하는데 사용)
     */
    @MessageMapping("/send")
    @SendTo("/topic/message")
    public String message(String message) {
        return message;
    }
}
