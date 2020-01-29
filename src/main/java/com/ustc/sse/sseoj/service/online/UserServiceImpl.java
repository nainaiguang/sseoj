package com.ustc.sse.sseoj.service.online;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.user.UserDao;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.service.online.superService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 *
 * 登陆或注册服务
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result adminregister(UsersModel user) {
        user.setSuccessRegister(false);//暂且设置注册失败
        try{
        UsersModel existUser=userDao.adminNameByName(user.getNo());
        if(existUser!=null)//用户已经存在
        {
            return new Result.Fail(Code.ACCOUNT_ALREADY_EXISTS);
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
    public Result adminlogin(UsersModel user) {
        String ano=user.getNo();
        String apassword=user.getPassword();
        try {
            UsersModel adminModel = userDao.adminLogin(ano, apassword);
            if (adminModel != null)//登陆成功
            {
                return new Result.Success(adminModel);
            } else//登陆失败
            {
                return new Result.Fail(Code.INCORRECT_ACCOUNT_OR_PASSWORD);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    @Override
    public Result studentregister(UsersModel user) {
        user.setSuccessRegister(false);//暂且设置注册失败
        try{
            UsersModel existUser=userDao.studentNameByName(user.getNo());
            if(existUser!=null)//用户已经存在
            {
                return new Result.Fail(Code.ACCOUNT_ALREADY_EXISTS);
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
    public Result studentlogin(UsersModel user) {
        String sno=user.getNo();
        String spassword=user.getPassword();
        try {
            UsersModel studentModel = userDao.studentLogin(sno, spassword);
            if (studentModel != null)//登陆成功
            {
                return new Result.Success(studentModel);
            } else//登陆失败
            {
                return new Result.Fail(Code.INCORRECT_ACCOUNT_OR_PASSWORD);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result teacherregister(UsersModel user) {
        user.setSuccessRegister(false);//暂且设置注册失败
        try{
            UsersModel existUser=userDao.teacherNameByName(user.getNo());
            if(existUser!=null)//用户已经存在
            {
                return new Result.Fail(Code.ACCOUNT_ALREADY_EXISTS);
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
    public Result teacherlogin(UsersModel user) {
        String tno=user.getNo();
        String tpassword=user.getPassword();
        try {
            UsersModel teacherModel = userDao.teacherLogin(tno,tpassword);
            if (teacherModel != null)//登陆成功
            {
                return new Result.Success(teacherModel);
            } else//登陆失败
            {
                return new Result.Fail(Code.INCORRECT_ACCOUNT_OR_PASSWORD);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }
}
