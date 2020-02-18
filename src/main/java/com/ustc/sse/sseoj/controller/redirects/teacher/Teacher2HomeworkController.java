package com.ustc.sse.sseoj.controller.redirects.teacher;/**
 * @author Qianbw
 * @create 2020-02-15 17:48
 * @desc 教师作业页面跳转的控制层
 **/

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.service.teacher.HomeworkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Qianbw
 * @create 2020-02-15 17:48
 * @desc 教师作业页面跳转的控制层
 **/
@Controller
@RequestMapping("/teacherHomework")
public class Teacher2HomeworkController {

    @Autowired
    HomeworkServiceImpl homeworkService;

    /**
     * 功能描述: 进入课程管理下的作业管理页面
     * @Param: [model, courseModel]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/6 13:58
     */
    @RequestMapping("/toCourseHomework")
    public String toCourseHomework(Model model, CourseModel courseModel){
        model.addAttribute("courseID", courseModel.getCourseID());
        return "teacher/course/homework";
    }

    /**
     * 功能描述: 进入作业添加页面
     * @Param: [model, courseModel]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/6 16:17
     */
    @RequestMapping("/toAddCourseHomework")
    public String toAddCourseHomework(Model model, CourseModel courseModel){
        model.addAttribute("courseID", courseModel.getCourseID());
        return "teacher/homework/addHomework";
    }

    /**
     * 功能描述: 进入作业编辑页面
     * @Param: [model, hm]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/6 18:42
     */
    @RequestMapping("/toEditCourseHomework")
    public String toEditCourseHomework(Model model, homeworkModel hm){
        Result result=homeworkService.get_one_homework_detail(hm);
        if(result instanceof Result.Success)
        {
            hm = (homeworkModel)((Result.Success) result).getData();
        }
        model.addAttribute("homeworkModel", hm);
        return "teacher/homework/editHomework";
    }

    /**
     * 功能描述: 进入教师作业管理页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/7 13:27
     */
    @RequestMapping("/teaHomework")
    public String teaHomework() {
        return "teacher/homework/teaHomework";
    }

    /**
     * 功能描述: 进入作业选择页面
     * @Param: [model, courseModel]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/7 19:39
     */
    @RequestMapping("/toSelectCourseHomework")
    public String toSelectCourseHomework(Model model, CourseModel courseModel){
        model.addAttribute("courseID", courseModel.getCourseID());
        return "teacher/course/selectHomework";
    }
}
