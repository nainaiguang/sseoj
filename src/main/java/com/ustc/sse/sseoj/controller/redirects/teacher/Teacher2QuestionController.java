package com.ustc.sse.sseoj.controller.redirects.teacher;

import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**

/**
 * @author Qianbw
 * @create 2020-02-15 14:38
 * @desc 教师管理题库页面跳转的控制层
 **/
@Controller
@RequestMapping("/teacherQuestion")
public class Teacher2QuestionController {

    /**
     * 功能描述: 进入题库管理页面
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/15 14:42
     */
    @RequestMapping("/toQuestion")
    public String toQuestion0(Model model, homeworkModel hm) {
        model.addAttribute("homeworkModel", hm);
        return "teacher/question/teaQuestion";
    }

}
