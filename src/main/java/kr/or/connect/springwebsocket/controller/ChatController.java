package kr.or.connect.springwebsocket.controller;

import kr.or.connect.springwebsocket.vo.StompMessage;
import kr.or.connect.springwebsocket.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatPage(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        return "chat/chat";
    }

    @ResponseBody
    @PostMapping("/chat/api")
    public StompMessage chatPage(@RequestBody StompMessage message) {
        return message;
    }
}
