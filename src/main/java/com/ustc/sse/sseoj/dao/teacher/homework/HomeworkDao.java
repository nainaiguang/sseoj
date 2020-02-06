package com.ustc.sse.sseoj.dao.teacher.homework;

import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/18 14:47
 * 作业的联合查询
 */
@Mapper
public interface HomeworkDao {


    //某门课下的所有作业，包括名字模糊查询 空即查询所有
    @Select("SELECT\n" +
            "\thomework.*\n" +
            "FROM\n" +
            "\tcourse_homework\n" +
            "INNER JOIN homework ON course_homework.homeworkID = homework.homeworkID\n" +
            "WHERE course_homework.courseID=#{cm.courseID} AND homework.name LIKE '%${hm.name}%'")
    public ArrayList<homeworkModel> search_homework_for_name_fully_in_course(CourseModel cm, homeworkModel hm);

    //某老师的所有作业，包括名字模糊查询 空即查询所有
    @Select("SELECT\n" +
            "\thomework.*\n" +
            "FROM\n" +
            "\tteacher_homework\n" +
            "INNER JOIN homework ON teacher_homework.homeworkID = homework.homeworkID\n" +
            "WHERE teacher_homework.tno=#{tm.tno} AND homework.name LIKE '%${hm.name}%'")
    public ArrayList<homeworkModel> search_homework_for_name_fully_in_teacher(TeacherModel tm,homeworkModel hm);

    //查询某作业那几门课在用
    @Select("SELECT\n" +
            "\tcourse.*\n" +
            "FROM\n" +
            "\tcourse\n" +
            "INNER JOIN course_homework\n" +
            "WHERE\n" +
            "\tcourse.courseID = course_homework.courseID\n" +
            "AND course_homework.homeworkID = #{hm.homeworkID}")
    public ArrayList<CourseModel> search_course_using_homework(homeworkModel hm);

}
