package com.ustc.sse.sseoj.controller.online;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/2 12:15
 */
public class LogOutController {
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/index.html";
    }
}
