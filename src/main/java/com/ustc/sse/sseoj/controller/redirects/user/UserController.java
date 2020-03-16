package com.ustc.sse.sseoj.controller.redirects.user;

import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
        return "menu";};

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
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";}

    /**
     * 功能描述: 跳转用户基本信息
     * @Param: []
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/3/5 16:30
     */
    @RequestMapping("/toUserInfo")
    public String toUserInfo(Model model, HttpServletRequest request) {
        UsersModel user = (UsersModel ) request.getSession().getAttribute("user");
        model.addAttribute("userModel",user);
        return "userInfo";}
}


