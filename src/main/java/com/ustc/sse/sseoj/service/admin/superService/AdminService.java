package com.ustc.sse.sseoj.service.admin.superService;


import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.user.studentModel;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.model.user.teacherModel;
import com.ustc.sse.sseoj.model.user.uniteModel.student_uniteModel;
import com.ustc.sse.sseoj.model.user.uniteModel.teacher_uniteModel;

import java.util.ArrayList;


public interface AdminService {
    //获取目前的所有学生(可以根据院系，年级来筛选,名字) 已测
    public Result select_student_according_condition(student_uniteModel sum, pageLimit pl);
    public Result select_count_according_condition(student_uniteModel sum);


    //修改学生密码(已测）
    public Result updateSecret(UsersModel um);

    //获取目前的学生中的所有院系（已测）
    public Result get_all_student_depts(pageLimit pl);
    public Result get_all_student_depts();

    //获取目前的学生中所有年级（已测）
    public Result get_all_student_grades(pageLimit pl);
    public Result get_all_student_grades();

    //获取某学生的详细信息 （已测）
    public Result get_all_info_from_student(studentModel sm);

    //修改该学生的所有基本信息
    public Result update_studentinfo(student_uniteModel sum);

    //根据学号获取该学生的所有选课信息（已测）
    public Result select_courses_from_student(studentModel sm, pageLimit pl);
    public Result select_courses_from_student(studentModel sm);

    //通过学生ID可以删除学生的选课信息（已测）
    public Result delete_student_courseInfo(select_courseModelKey scmk);

    //通过学生ID批量删除学生选课信息（）
    public Result delete_branch_student_courseInfo(ArrayList<select_courseModelKey> arrayList);

    //删除学生账号信息（已测）
    public Result delete_studentInfo(studentModel sm);
    //批量删除学生账号信息（已测）
    public Result delete_branch_studentInfo(ArrayList<studentModel> arrayList);

    //为学生批量选课
    public Result select_course_for_student(select_courseModelKey scmk);
    //批量为学生选课
    public Result select_branchCourse_forStudent(ArrayList<select_courseModelKey> arrayList);

    //查看所有的教师(可以根据院系来筛选)
    public Result select_teacher_according_condition(teacher_uniteModel tum, pageLimit pl);
    public Result select_count_according_condition(teacher_uniteModel tum);


    //获取目前的教师中的所有院系（已测）
    public Result get_all_teacher_depts(pageLimit pl);
    public Result get_all_teacher_depts();

    //获取某教师的详细信息（已测）
    public Result get_all_info_from_teacher(teacher_uniteModel tum);

    //根据学号和课程号为学生选课（已测）
    public Result insert_course_toStudent(select_courseModelKey scmk);
    //根据学号和课程号批量为学生选课（已测）
    public Result insert_branch_course_toStudent(ArrayList<select_courseModelKey> arrayList);

    //删除某教师账号（已测）
    public Result delete_teacherInfo(teacherModel tm);
    //批量删除某教师账号（已测）
    public Result delete_branch_teacherInfo(ArrayList<teacherModel> arrayList);

    //修改该教师的所有基本信息
    public Result update_teacherinfo(teacher_uniteModel tum);

}
