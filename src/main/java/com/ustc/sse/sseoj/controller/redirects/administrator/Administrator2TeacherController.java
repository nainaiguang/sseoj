package com.ustc.sse.sseoj.controller.redirects.administrator;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.user.studentModel;
import com.ustc.sse.sseoj.model.user.uniteModel.student_uniteModel;
import com.ustc.sse.sseoj.model.user.uniteModel.teacher_uniteModel;
import com.ustc.sse.sseoj.service.admin.AdminServiceImpl;
import com.ustc.sse.sseoj.service.teacher.TeacherCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wangning
 * @create 2020-03-07 19:11
 * @desc 学生管理页面跳转的控制层
 **/
@Controller
@RequestMapping("/administratorTeacher")
public class Administrator2TeacherController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private TeacherCourseServiceImpl teacherCourseService;

    /**
     * 功能描述: 进入教师管理页面
     * @Return: java.lang.String
     * @Author: Wangning
     * @Date: 2020/03/07 19:13
     */
    @RequestMapping("/teacherManage")
    public String teacherManage() {
        return "administrator/teacher/teacherManage";
    }
    /**
     * 功能描述: 进入教师详细信息页面
     * @Param: [model, tm]
     * @Return: java.lang.String
     * @Author: Wangning
     * @Date: 2020/3/23 18:42
     */
    @RequestMapping("/toShowTeacherInfo")
    public String toShowTeacherInfo(Model model, teacher_uniteModel tm){
        Result result=adminService.get_all_info_from_teacher(tm);
        boolean judge_defaultpassword=true;
        if(result instanceof Result.Success)
        {
            tm = (teacher_uniteModel) ((Result.Success) result).getData();
        }

        if (tm.getPassword() == null)
        {
            judge_defaultpassword=false;
        }
        String defaultpassword=tm.getNo().toLowerCase();
        model.addAttribute("teacherModel", tm);
        model.addAttribute("judge_defaultpassword",judge_defaultpassword);
        model.addAttribute("defaultpassword",defaultpassword);
        return "administrator/teacher/showTeacherInfo";
    }

    /**
     * 功能描述: 进入教师课程信息页面
     * @Param: [model]
     * @Return: java.lang.String
     * @Author: Wangning
     * @Date: 2020/3/23 18:42
     */
    @RequestMapping("/toEditTeacherCourse")
    public String toEditTeacherCourse(Model model,String  tno){
       model.addAttribute("tno",tno);
        return "administrator/teacher/courseTeacher";
    }

}
