package com.ustc.sse.sseoj.service.student.superService;


import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.singleModel.student.select_courseModelMapper;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.model.teacher.CourseModel;


public interface StudentCourceService {

    //管理员为学生选课
    public Result add_one_course_for_student(select_courseModelKey scmk);

    //管理员删除学生课程
    public Result delete_one_cource_for_student(select_courseModelKey scmk);

    //



}
