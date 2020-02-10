package com.ustc.sse.sseoj.service.teacher.superService;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.course_homeworkModelKey;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/18 15:30
 * 对作业整体进行操作
 */

public interface HomeworkService {

    public Result get_one_homework_detail(homeworkModel hm);

    //教师添加作业
    //其中 如果作业本身不存在，则创建作业，否则不创建
    // 且课程id不存在，则添加作业，连接教师与作业
    //如果课程id存在，则则添加作业，连接教师与作业
    public Result teacher_add_homework(TeacherModel tm, homeworkModel hm, CourseModel cm);

    //教师删除作业，直接删除，包括关系,包括批量
    public Result delete_homework(ArrayList<homeworkModel> arrayList);

    //删除课程与作业的关系，包括批量
    public Result delete_homework_link_with_course(ArrayList<course_homeworkModelKey> arrayList);

    //更新作业信息，其中包括 名称，描述，开始时间，结束时间
    public Result update_homework(homeworkModel hm);

    //显示某老师的作业课程，包括模糊查询
    public Result search_homework(TeacherModel tm, CourseModel cm,homeworkModel hm);

    //显示这个作业哪几门在用
    public Result people_use_homework(homeworkModel hm);

    //显示目前没有在改课程下应用的该教师的作业
    public Result search_homework_without_using(TeacherModel tm,CourseModel cm,homeworkModel hm);
}
