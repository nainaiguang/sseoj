package com.ustc.sse.sseoj.controller.redirects.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getHello() {
        return "hello.html";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";};

    @RequestMapping("/register")
    public String register() {
        return "register";};

    /**
     * 功能描述: 主页
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/1/29 18:22
     */
    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("msg","查看使用是否可行。");
        return "main";};

    @RequestMapping("/tempLogin")
    public String toTempLogin() {
        return "tempLogin";};

    @RequestMapping("/tempCourse")
    public String tempCourse() {
        return "loginResourse/index";};

    /**
     * 功能描述: 首页
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/1/29 18:22
     */
    @RequestMapping("/home")
    public String home() {
        return "home";}
}


