package com.ustc.sse.sseoj.service.student.superService;


import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.singleModel.student.select_courseModelMapper;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.teacher.homework_link_bankModel;
import com.ustc.sse.sseoj.model.user.studentModel;


public interface StudentCourceService {

    //获取该学生的所有选课信息及相应教师
    public Result select_courseInfo_from_student(studentModel sm, pageLimit pl);
    public Result select_courseInfo_from_student(studentModel sm);

    //显示该课程的所有作业
    public Result select_homeworkInfo_from_courseID(CourseModel cm,pageLimit pl);
    public Result select_homeworkInfo_from_courseID(CourseModel cm);

    //显示本次作业的所有题目，按题号排序.
    public Result select_question_from_homeworkID(homeworkModel hm,pageLimit pl);
    public Result select_question_from_homeworkID(homeworkModel hm);

    //根据题号获取本次作业的那一题
    public Result select_question_from_num(homework_link_bankModel hlbm);













//    //管理员为学生选课
//    public Result add_one_course_for_student(select_courseModelKey scmk);
//
//    //管理员删除学生课程
//    public Result delete_one_cource_for_student(select_courseModelKey scmk);

    //



}
