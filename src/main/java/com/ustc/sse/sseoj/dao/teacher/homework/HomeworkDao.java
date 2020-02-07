package com.ustc.sse.sseoj.dao.teacher.homework;

import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
            "INNER JOIN teacher_homework ON teacher_homework.homeworkID = course_homework.homeworkID\n" +
            "WHERE\n" +
            "\tcourse_homework.courseID = #{cm.courseID}\n" +
            "AND teacher_homework.tno = #{tm.tno} \n" +
            "AND homework.name LIKE '%${hm.name}%'")
    public ArrayList<homeworkModel> search_homework_for_name_fully_in_course(@Param("tm") TeacherModel tm,@Param("cm") CourseModel cm,@Param("hm") homeworkModel hm);

    //某老师的所有作业，包括名字模糊查询 空即查询所有
    @Select("SELECT\n" +
            "\thomework.*\n" +
            "FROM\n" +
            "\tteacher_homework\n" +
            "INNER JOIN homework ON teacher_homework.homeworkID = homework.homeworkID\n" +
            "WHERE teacher_homework.tno=#{tm.tno} AND homework.name LIKE '%${hm.name}%'")
    public ArrayList<homeworkModel> search_homework_for_name_fully_in_teacher(@Param("tm") TeacherModel tm,@Param("hm") homeworkModel hm);

    //查询某作业那几门课在用
    @Select("SELECT\n" +
            "\tcourse.*\n" +
            "FROM\n" +
            "\tcourse\n" +
            "INNER JOIN course_homework\n" +
            "WHERE\n" +
            "\tcourse.courseID = course_homework.courseID\n" +
            "AND course_homework.homeworkID = #{homeworkid}")
    public ArrayList<CourseModel> search_course_using_homework(homeworkModel hm);

//查询目前不属于这个课程但属于这个老师的作业
    @Select("SELECT\n" +
            "\tA.*\n" +
            "FROM\n" +
            "\thomework A\n" +
            "INNER JOIN teacher_homework B ON A.homeworkID = B.homeworkID\n" +
            "WHERE\n" +
            "\tB.tno = #{tm.tno}\n" +
            "AND A.homeworkID NOT IN (\n" +
            "\tSELECT\n" +
            "\t\thomeworkID\n" +
            "\tFROM\n" +
            "\t\tcourse_homework\n" +
            "\tWHERE\n" +
            "\t\tcourseID = #{cm.courseID}\n" +
            ")")
    public ArrayList<homeworkModel> search_homework_without_using(@Param("tm") TeacherModel tm,@Param("cm") CourseModel cm);
}
