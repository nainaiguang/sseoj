package com.ustc.sse.sseoj.service.teacher.superService;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.Curricula_variableModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 16:01
 */
public interface teacherCourseService {
    //老师增加课程
    public Result teacher_add_course(CourseModel courseModel, Curricula_variableModel curricula_variableModel);

    //显示该老师的所有课程
    public Result teacher_show_somebody_all_course(Curricula_variableModel curricula_variableModel);

    //更改课程名
    public Result teacher_update_course(CourseModel courseModel);

    //删除课程 课程关系
    public Result teacher_delete_course(CourseModel courseModel);

    //批量删除课程 课程关系
    public Result teacher_batch_delete_course(ArrayList<CourseModel> arrayList);

    //查找某个老师自己的课程 模糊查询 通过课程名
    public Result teacher_search_course_fully(CourseModel courseModel, Curricula_variableModel curricula_variableModel);

    //查找某个课程 通过courseID
    public Result teacher_search_scourse_by_courseID(CourseModel courseModel);


}
