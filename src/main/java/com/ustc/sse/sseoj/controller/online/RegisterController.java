package com.ustc.sse.sseoj.controller.online;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.common.Role;
import com.ustc.sse.sseoj.model.user.AdminModel;
import com.ustc.sse.sseoj.model.user.StudentModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import com.ustc.sse.sseoj.service.online.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */
@Controller
@RequestMapping("/users")
public class RegisterController {
    @Autowired
    private UserServiceImpl userService;



    @RequestMapping(value = "/adminRegister", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public AdminModel adminregister(AdminModel temp) {

        temp.setRole(String.valueOf(Role.manager));
        Result result=userService.adminregister(temp);

        if(result instanceof Result.Success)
        {
            AdminModel res= (AdminModel) ((Result.Success) result).getData();
            res.setSuccessRegister(true);
            res.setMes("success");
            return res;
        }
        else if(result instanceof Result.Fail)
        {
            AdminModel res= new AdminModel();
            res.setSuccessRegister(false);
            res.setMes(((Result.Fail) result).getReason());
            return res;
        }
        else
        {
            AdminModel res= new AdminModel();
            res.setSuccessRegister(false);
            res.setMes("error");
            return res;
        }

    }

    @RequestMapping(value = "/studentRegister", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public StudentModel studentregister(StudentModel temp) {

        temp.setRole(String.valueOf(Role.student));

        Result result=userService.studentregister(temp);

        if(result instanceof Result.Success)
        {
            StudentModel res= (StudentModel) ((Result.Success) result).getData();
            res.setSuccessRegister(true);
            res.setMes("success");
            return res;
        }
        else if(result instanceof Result.Fail)
        {
            StudentModel res= new StudentModel();
            res.setSuccessRegister(false);
            res.setMes(((Result.Fail) result).getReason());
            return res;
        }
        else
        {
            StudentModel res= new StudentModel();
            res.setSuccessRegister(false);
            res.setMes("error");
            return res;
        }

    }

    @RequestMapping(value = "/teacherRegister", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public TeacherModel teacherregister(TeacherModel temp) {

        temp.setRole(String.valueOf(Role.teacher));

        Result result=userService.teacherregister(temp);

        if(result instanceof Result.Success)
        {
            TeacherModel res= (TeacherModel) ((Result.Success) result).getData();
            res.setSuccessRegister(true);
            res.setMes("success");
            return res;
        }
        else if(result instanceof Result.Fail)
        {
            TeacherModel res= new TeacherModel();
            res.setSuccessRegister(false);
            res.setMes(((Result.Fail) result).getReason());
            return res;
        }
        else
        {
            TeacherModel res= new TeacherModel();
            res.setSuccessRegister(false);
            res.setMes("error");
            return res;
        }

    }
}
