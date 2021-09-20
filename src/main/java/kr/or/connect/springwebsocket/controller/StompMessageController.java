package kr.or.connect.springwebsocket.controller;

import kr.or.connect.springwebsocket.vo.StompMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StompMessageController {

    /**
     * @param message
     * @return
     * @MessageMapping STOMP Client의 send에 대한 Target URL
     * @SendTo STOMP Client의 Subscribe에 대한 Target URL(지정되지 않은 경우, @MessageMapping에 지정된 URL + @Configuration에서 설정된 Subscribe prefix URL을 이용)
     */
    @MessageMapping("/send")
    @SendTo("/topic/message")
    public StompMessage message(StompMessage message) {
        System.out.println("message = " + message);

        return message;
    }
}
