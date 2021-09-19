package kr.or.connect.springwebsocket.controller;

import kr.or.connect.springwebsocket.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
