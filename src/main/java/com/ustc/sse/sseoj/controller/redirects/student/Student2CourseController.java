package com.ustc.sse.sseoj.controller.redirects.student;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Mes;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.homework_link_bankModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import com.ustc.sse.sseoj.service.student.StudentCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

/**
 * @author Qianbw
 * @create 2020-03-19 17:26
 * @desc 学生课程视图控制器
 **/
@RequestMapping("/studentCourse")
@Controller
public class Student2CourseController {

    @Autowired
    StudentCourseServiceImpl studentCourseService;

    /**
     * 功能描述: 跳转到课程管理页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/3/19 17:28
     */
    @RequestMapping("/toCourse")
    public String toCourse(){
       return "student/course/stuCourse";
    }

    /**
     * 功能描述: 跳转到课程下的作业页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/3/19 17:28
     */
    @RequestMapping("/toHomework")
    public String toHomework(Model model, String courseID){
        model.addAttribute("courseID",courseID);
        return "student/homework/stuHomework";
    }

    /**
     * 功能描述: 跳转到题目页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/3/19 17:28
     */
    @RequestMapping("/toQuestion")
    public String toQuestion(Model model, String homeworkid){
        model.addAttribute("homeworkid",homeworkid);
        return "student/question/stuQuestion";
    }

    /**
     * 功能描述: 跳转到做题目页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/3/19 17:28
     */
    @RequestMapping("/toDoQuestion")
    public String toDoQuestion(Model model, homework_link_bankModel hlbm){

        Result result=studentCourseService.select_question_from_num(hlbm);
        questionModel questionModel= (questionModel) ((Result.Success) result).getData();
        model.addAttribute("questionModel", questionModel);
        return "student/question/doQuestion";
    }
}
