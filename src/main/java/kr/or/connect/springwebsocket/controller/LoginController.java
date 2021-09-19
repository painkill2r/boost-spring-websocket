package kr.or.connect.springwebsocket.controller;

import kr.or.connect.springwebsocket.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(HttpSession session, RedirectAttributes redirectAttributes) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser != null) {
            return "redirect:/chat";
        }

        return "user/login";
    }

    @PostMapping("/login")
    public String login(HttpSession session,
                        @ModelAttribute("user") User user,
                        RedirectAttributes redirectAttributes) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser != null) {
            return "redirect:/chat";
        }

        if (user == null || "".equals(user.getId()) || "".equals(user.getName())) {
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다.");
            return "redirect:/login";
        }

        session.setAttribute("loginUser", user);

        return "redirect:/chat";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/login";
    }
}
