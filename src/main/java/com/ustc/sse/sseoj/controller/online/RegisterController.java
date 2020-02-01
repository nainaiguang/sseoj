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


    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes register(UsersModel temp)
    {
        if(temp.getRole()==null)
        {
            return new Mes(false,Code.MISS_ROLE,0,null);
        }

        if(temp.getRole().equals(Role.teacher.toString()))
        {
            return teacherregister(temp);
        }
        else if(temp.getRole().equals(Role.manager.toString()))
        {
            return adminregister(temp);
        }
        else if(temp.getRole().equals(Role.student.toString()))
        {
            return studentregister(temp);
        }
        else
        {
            return new Mes(false,Code.WRONG_ROLE,0,null);
        }
    }


    @RequestMapping(value = "/adminRegister", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Mes adminregister(UsersModel temp) {

        temp.setRole(String.valueOf(Role.manager));
        Result result=userService.adminregister(temp);

        if(result instanceof Result.Success)
        {
            UsersModel res= (UsersModel) ((Result.Success) result).getData();
            res.setSuccessRegister(true);
            Mes mes=new Mes(true, Code.SUCCESS,1,res);
             System.out.println(mes.toString());
             return mes;
        }
        else if(result instanceof Result.Fail)
        {
            UsersModel res= new UsersModel();
            res.setSuccessRegister(false);
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,res);
             System.out.println(mes.toString());
             return mes;
        }
        else
        {
            UsersModel res= new UsersModel();
            res.setSuccessRegister(false);
            Mes mes=new Mes(false,Code.ERROR,0,res);
            System.out.println(mes.toString());
           return mes;
        }

    }

    @RequestMapping(value = "/studentRegister", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Mes studentregister(UsersModel temp) {

        temp.setRole(String.valueOf(Role.student));

        Result result=userService.studentregister(temp);

        if(result instanceof Result.Success)
        {
            UsersModel res= (UsersModel) ((Result.Success) result).getData();
            res.setSuccessRegister(true);
            Mes mes=new Mes(true, Code.SUCCESS,1,res);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            UsersModel res= new UsersModel();
            res.setSuccessRegister(false);
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,res);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            UsersModel res= new UsersModel();
            res.setSuccessRegister(false);
            Mes mes=new Mes(false,Code.ERROR,0,res);
            System.out.println(mes.toString());
            return mes;
        }

    }

    @RequestMapping(value = "/teacherRegister", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Mes teacherregister(UsersModel temp) {

        temp.setRole(String.valueOf(Role.teacher));

        Result result=userService.teacherregister(temp);

        if(result instanceof Result.Success)
        {
            UsersModel res= (UsersModel) ((Result.Success) result).getData();
            res.setSuccessRegister(true);
            Mes mes=new Mes(true, Code.SUCCESS,1,res);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            UsersModel res= new UsersModel();
            res.setSuccessRegister(false);
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,res);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            UsersModel res= new UsersModel();
            res.setSuccessRegister(false);
            Mes mes=new Mes(false,Code.ERROR,0,res);
             System.out.println(mes.toString());
             return mes;
        }

    }
}
