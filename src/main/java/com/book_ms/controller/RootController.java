package com.book_ms.controller;

import com.book_ms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Author xiao
 * @Time 2019/11/28
 * @Describe TODO
 **/
@Controller
public class RootController {
    @RequestMapping(value = {"/","/index"})
    public String index() {
        return "redirect:login.html";
    }

    @Autowired
    UserService userService;

    @RequestMapping("/login.html")
    public String loginPage() {
        return "login/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HashMap<String, Object> data = userService.userLogin(username, password);
        if(data.get("user") != null) {
            session.setAttribute("user", data.get("user"));
            session.setAttribute("userType", data.get("userType"));
        }
        request.setAttribute("msg", data.get("msg"));
        return data.get("gotoPath").toString();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login/login";
    }

}
