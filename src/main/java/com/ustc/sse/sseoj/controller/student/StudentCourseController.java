package com.ustc.sse.sseoj.controller.student;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Mes;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.service.student.StudentCourseServiceImpl;
import com.ustc.sse.sseoj.service.student.superService.StudentCourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Author 沙政鑫
 * @Data 2020/2/18  22:58
 */

@Controller
@RequestMapping("/student")
public class StudentCourseController {
    @Autowired

    private StudentCourseServiceImpl studentCourseService;


    //管理员为学生选课
    @RequestMapping(value = "/addOneCourseForStudent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes add_one_course_for_student(CourseModel courseModel, HttpServletRequest request){

        select_courseModelKey smk=new select_courseModelKey();
        UsersModel um= (UsersModel) request.getSession().getAttribute("user");
        smk.setCourseid(courseModel.getCourseID());
        smk.setSno(um.getNo());
        Result result=studentCourseService.add_one_course_for_student(smk);
        if(result instanceof Result.Success){
            boolean res= (boolean) ((Result.Success) result).getData();
            Mes mes=new Mes(true, Code.SUCCESS,1,res);
            return mes;
        }else if(result instanceof Result.Fail){
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            return mes;
        }else {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            return mes;
        }
    }

    //管理员为学生删除课程
    @RequestMapping(value = "/deleteOneCourceForStudent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes delete_one_cource_for_student(CourseModel courseModel,HttpServletRequest request){
        select_courseModelKey scmk=new select_courseModelKey();
        UsersModel um= (UsersModel) request.getSession().getAttribute("user");
        scmk.setSno(um.getNo());
        scmk.setCourseid(courseModel.getCourseID());
        Result result=studentCourseService.delete_one_cource_for_student(scmk);
        if(result instanceof Result.Success){
            boolean res= (boolean) ((Result.Success) result).getData();
            Mes mes=new Mes(true,Code.SUCCESS,1,res);
            return mes;
        }else if(result instanceof Result.Fail){
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            return mes;
        }else {
            Mes mes =new Mes(false,Code.ERROR,0,null);
            return mes;
        }
    }

}
