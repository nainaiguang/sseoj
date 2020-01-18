package com.ustc.sse.sseoj.service.superService;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.user.AdminModel;
import com.ustc.sse.sseoj.model.user.StudentModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;

public interface UserService {

    //管理员登陆注册
    Result adminregister(AdminModel user);
    Result adminlogin(AdminModel user);

    //学生登陆注册
    Result studentregister(StudentModel user);
    Result studentlogin(StudentModel user);

    //教师登陆注册
    Result teacherregister(TeacherModel user);
    Result teacherlogin(TeacherModel user);
}
