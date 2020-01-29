package com.ustc.sse.sseoj.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Qianbw
 * @create 2020-01-28 19:11
 * @desc 教师模块的控制层
 **/
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    /**
     * 功能描述: 进入教师课程管理页面
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/1/28 19:13
     */
    @RequestMapping("/teaCourse")
    public String teaCourse() {
        return "teacher/teaCourse";};
}
