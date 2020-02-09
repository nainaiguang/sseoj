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
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherCourseServiceImpl teacherCourseService;

    @Autowired
    HomeworkServiceImpl homeworkService;

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
     * 功能描述: 进入课程管理下的作业添加页面
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
     * 功能描述: 进入课程管理下的作业编辑页面
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
