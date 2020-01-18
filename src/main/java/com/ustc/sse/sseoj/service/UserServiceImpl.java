package com.ustc.sse.sseoj.service;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.UserDao;
import com.ustc.sse.sseoj.model.user.AdminModel;
import com.ustc.sse.sseoj.model.user.StudentModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import com.ustc.sse.sseoj.service.superService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result adminregister(AdminModel user) {
        user.setSuccessRegister(false);//暂且设置注册失败
        try{
        AdminModel existUser=userDao.adminNameByName(user.getAno());
        if(existUser!=null)//用户已经存在
        {
            return new Result.Fail("Account already exists");
        }
        else
        {
            userDao.adminRegist(user);
            user.setSuccessRegister(true);
            return new Result.Success(user);
        }

        }
         catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result adminlogin(AdminModel user) {
        String ano=user.getAno();
        String apassword=user.getApassword();
        try {
            AdminModel adminModel = userDao.adminLogin(ano, apassword);
            if (adminModel != null)//登陆成功
            {
                return new Result.Success(adminModel);
            } else//登陆失败
            {
                return new Result.Fail("Incorrect account or password");
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    @Override
    public Result studentregister(StudentModel user) {
        user.setSuccessRegister(false);//暂且设置注册失败
        try{
            StudentModel existUser=userDao.studentNameByName(user.getSno());
            if(existUser!=null)//用户已经存在
            {
                return new Result.Fail("Account already exists");
            }
            else
            {
                userDao.studentRegist(user);
                user.setSuccessRegister(true);
                return new Result.Success(user);
            }

        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result studentlogin(StudentModel user) {
        String sno=user.getSno();
        String spassword=user.getSpassword();
        try {
            StudentModel studentModel = userDao.studentLogin(sno, spassword);
            if (studentModel != null)//登陆成功
            {
                return new Result.Success(studentModel);
            } else//登陆失败
            {
                return new Result.Fail("Incorrect account or password");
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result teacherregister(TeacherModel user) {
        user.setSuccessRegister(false);//暂且设置注册失败
        try{
            TeacherModel existUser=userDao.teacherNameByName(user.getTno());
            if(existUser!=null)//用户已经存在
            {
                return new Result.Fail("Account already exists");
            }
            else
            {
                userDao.teacherRegist(user);
                user.setSuccessRegister(true);
                return new Result.Success(user);
            }

        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result teacherlogin(TeacherModel user) {
        String tno=user.getTno();
        String tpassword=user.getTpassword();
        try {
            TeacherModel teacherModel = userDao.teacherLogin(tno,tpassword);
            if (teacherModel != null)//登陆成功
            {
                return new Result.Success(teacherModel);
            } else//登陆失败
            {
                return new Result.Fail("Incorrect account or password");
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }
}
