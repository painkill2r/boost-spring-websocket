package kr.or.connect.springwebsocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatPage(HttpServletRequest request) {
        System.out.println("REQUEST URL: " + request.getRequestURI());

        return "chat/chat";
    }
}
