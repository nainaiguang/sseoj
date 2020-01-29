package com.ustc.sse.sseoj.controller.online;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Mes;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.Data.Role;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.service.online.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.invoke.SwitchPoint;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes Login(UsersModel temp)
    {
        if(temp.getRole()==null)
        {
            return new Mes(false,Code.MISS_ROLE,null);
        }

        if(temp.getRole().equals(Role.teacher.toString()))
        {
            return teacherlogin(temp);
        }
        else if(temp.getRole().equals(Role.manager.toString()))
        {
            return adminlogin(temp);
        }
        else if(temp.getRole().equals(Role.student.toString()))
        {
            return studentlogin(temp);
        }
        else
        {
            return new Mes(false,Code.WRONG_ROLE,null);
        }
    }



    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes adminlogin(UsersModel temp) {

        Result result=userService.adminlogin(temp);

        if(result instanceof Result.Success)
        {
            UsersModel res= (UsersModel) ((Result.Success) result).getData();
            res.setSuccessLogin(true);
            Mes mes=new Mes(true, Code.SUCCESS,res);
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            UsersModel res= new UsersModel();
            res.setSuccessLogin(false);
            //res.setMes(((Result.Fail) result).getReason());
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),res);
            return mes;
        }
        else
        {
            UsersModel res= new UsersModel();
            res.setSuccessLogin(false);
            Mes mes=new Mes(false,Code.ERROR,res);
            return mes;
        }

    }

    @RequestMapping(value = "/studentLogin", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Mes studentlogin(UsersModel temp) {


        Result result=userService.studentlogin(temp);

        if(result instanceof Result.Success)
        {
            UsersModel res= (UsersModel) ((Result.Success) result).getData();
            res.setSuccessLogin(true);
            Mes mes=new Mes(true, Code.SUCCESS,res);
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            UsersModel res= new UsersModel();
            res.setSuccessLogin(false);
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),res);
            return mes;
        }
        else
        {
            UsersModel res= new UsersModel();
            res.setSuccessLogin(false);
            Mes mes=new Mes(false,Code.ERROR,res);
            return mes;
        }
    }

    @RequestMapping(value = "/teacherLogin", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Mes teacherlogin(UsersModel temp) {


        Result result=userService.teacherlogin(temp);

        if(result instanceof Result.Success)
        {
            UsersModel res= (UsersModel) ((Result.Success) result).getData();
            res.setSuccessLogin(true);
            Mes mes=new Mes(true, Code.SUCCESS,res);
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            UsersModel res= new UsersModel();
            res.setSuccessLogin(false);
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),res);
            return mes;
        }
        else
        {
            UsersModel res= new UsersModel();
            res.setSuccessLogin(false);
            Mes mes=new Mes(false,Code.ERROR,res);
            return mes;
        }
    }
}
