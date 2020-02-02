package com.ustc.sse.sseoj.service.online.superService;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;

public interface UserService {

    //管理员登陆注册
    public Result adminregister(UsersModel user);
    public Result adminlogin(UsersModel user);

    //学生登陆注册
    public Result studentregister(UsersModel user);
    public Result studentlogin(UsersModel user);

    //教师登陆注册
    public Result teacherregister(UsersModel user);
    public Result teacherlogin(UsersModel user);
}
