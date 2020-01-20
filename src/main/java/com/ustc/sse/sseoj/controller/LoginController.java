package com.ustc.sse.sseoj.controller;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.user.AdminModel;
import com.ustc.sse.sseoj.model.user.StudentModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import com.ustc.sse.sseoj.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */
//@RestController//以json的方式输出
@Controller
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public AdminModel adminlogin(AdminModel temp) {

        Result result=userService.adminlogin(temp);

        if(result instanceof Result.Success)
        {
            AdminModel res= (AdminModel) ((Result.Success) result).getData();
            res.setSuccessLogin(true);
            res.setMes("success");
            return res;
        }
        else if(result instanceof Result.Fail)
        {
            AdminModel res= new AdminModel();
            res.setSuccessLogin(false);
            res.setMes(((Result.Fail) result).getReason());
            return res;
        }
        else
        {
            AdminModel res= new AdminModel();
            res.setSuccessLogin(false);
            res.setMes("error");
            return res;
        }

    }

    @RequestMapping(value = "/studentLogin", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public StudentModel studentlogin(StudentModel temp) {


        Result result=userService.studentlogin(temp);

        if(result instanceof Result.Success)
        {
            StudentModel res= (StudentModel) ((Result.Success) result).getData();
            res.setSuccessLogin(true);
            res.setMes("success");
            return res;
        }
        else if(result instanceof Result.Fail)
        {
            StudentModel res= new StudentModel();
            res.setSuccessLogin(false);
            res.setMes(((Result.Fail) result).getReason());
            return res;
        }
        else
        {
            StudentModel res= new StudentModel();
            res.setSuccessLogin(false);
            res.setMes("error");
            return res;
        }
    }

    @RequestMapping(value = "/teacherLogin", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public TeacherModel teacherlogin(TeacherModel temp) {


        Result result=userService.teacherlogin(temp);

        if(result instanceof Result.Success)
        {
            TeacherModel res= (TeacherModel) ((Result.Success) result).getData();
            res.setSuccessLogin(true);
            res.setMes("success");
            return res;
        }
        else if(result instanceof Result.Fail)
        {
            TeacherModel res= new TeacherModel();
            res.setSuccessLogin(false);
            res.setMes(((Result.Fail) result).getReason());
            return res;
        }
        else
        {
            TeacherModel res= new TeacherModel();
            res.setSuccessLogin(false);
            res.setMes("error");
            return res;
        }
    }
}
