package com.ustc.sse.sseoj.controller.redirects.teacher;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.service.teacher.HomeworkServiceImpl;
import com.ustc.sse.sseoj.service.teacher.TeacherCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Qianbw
 * @create 2020-01-28 19:11
 * @desc 教师课程页面跳转的控制层
 **/
@Controller
@RequestMapping("/teacherCourse")
public class Teacher2CourseController {

    @Autowired
    private TeacherCourseServiceImpl teacherCourseService;

    /**
     * 功能描述: 进入教师课程管理页面
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/1/28 19:13
     */
    @RequestMapping("/teaCourse")
    public String teaCourse() {
        return "teacher/course/teaCourse";
    }

    /**
     * 功能描述: 进入教师课程添加页面
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/1/30 12:00
     */
    @RequestMapping("/toAddCourse")
    public String toAddCourse() {
        return "teacher/course/addCourse";
    }

    /**
     * 功能描述: 进入课程修改页面
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/1/30 13:35
     */
    @RequestMapping("/toEditCourse")
    public String toEditCourse(Model model, CourseModel courseModel) {
        Result result = teacherCourseService.teacher_search_course_by_courseID(courseModel);
        if(result instanceof Result.Success)
        {
            courseModel =(CourseModel) ((Result.Success) result).getData();
            model.addAttribute("courseModel", courseModel);
        }
        return "teacher/course/editCourse";
    }



}
