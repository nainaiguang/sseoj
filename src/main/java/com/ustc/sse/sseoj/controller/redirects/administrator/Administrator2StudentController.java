package com.ustc.sse.sseoj.controller.redirects.administrator;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.user.studentModel;
import com.ustc.sse.sseoj.model.user.uniteModel.student_uniteModel;
import com.ustc.sse.sseoj.service.admin.AdminServiceImpl;
import com.ustc.sse.sseoj.service.teacher.TeacherCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


/**
 * @author Wangning
 * @create 2020-03-07 19:11
 * @desc 学生管理页面跳转的控制层
 **/
@Controller
@RequestMapping("/administratorStudent")
public class Administrator2StudentController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private TeacherCourseServiceImpl teacherCourseService;


    /**
     * 功能描述: 进入学生管理页面
     * @Return: java.lang.String
     * @Author: Wangning
     * @Date: 2020/03/07 19:13
     */
    @RequestMapping("/studentManage")
    public String studentManage() {
        return "administrator/student/studentManage";
    }

    /**
     * 功能描述: 进入查看学生详细信息页面
     * @Param: [model, sm]
     * @Return: java.lang.String
     * @Author: Wangning
     *@Date: 2020/2/6 18:42
     * */
    @RequestMapping("/toShowStudentInfo")
    public String toShowStudentInfo(Model model, studentModel sm){
        Result result=adminService.get_all_info_from_student(sm);
        student_uniteModel sum=new student_uniteModel();
        boolean judge_defaultpassword=true;

        if(result instanceof Result.Success)
        {
            sum = (student_uniteModel)((Result.Success) result).getData();
        }
        if (sum.getPassword() == null)
        {
            judge_defaultpassword=false;
        }
        String defaultpassword=sum.getNo().toLowerCase();
        model.addAttribute("studentModel", sum);
        model.addAttribute("judge_defaultpassword",judge_defaultpassword);
        model.addAttribute("defaultpassword",defaultpassword);
        return "administrator/student/showStudentInfo";
    }
    /**
     * 功能描述: 进入编辑学生详细信息页面
     * @Param: [model, sm]
     * @Return: java.lang.String
     * @Author: Wangning
     * @Date: 2020/3/22 18:42
     */
    @RequestMapping("/toEditStudentInfo")
    public String  toEditStudentInfo(Model model, studentModel sm){
        Result result=adminService.get_all_info_from_student(sm);
        student_uniteModel sum=new student_uniteModel();
        boolean judge_defaultpassword=true;
        if(result instanceof Result.Success)
        {
            sum = (student_uniteModel)((Result.Success) result).getData();
        }
        if (sum.getPassword() == null)
        {
            judge_defaultpassword=false;
        }
        String defaultpassword=sum.getNo().toLowerCase();
        model.addAttribute("studentModel", sum);
        model.addAttribute("judge_defaultpassword",judge_defaultpassword);
        model.addAttribute("defaultpassword",defaultpassword);
        return "administrator/student/editStudent";
    }
    /**
     * 功能描述: 进入编辑学生选课信息页面
     * @Param: [model, sm]
     * @Return: java.lang.String
     * @Author: Wangning
     * @Date: 2020/3/22 18:42
     */
    @RequestMapping("/toEditStudentCourse")
    public String  toEditStudentCourse(Model model, String no){
        model.addAttribute("no",no);
        return "administrator/student/courseStudent";
    }
}
