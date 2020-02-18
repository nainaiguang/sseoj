package com.ustc.sse.sseoj.dao.teacher.course;

import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.Curricula_variableModel;
import org.apache.ibatis.annotations.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface courseDAO {
    @Insert("insert into course (courseID,name,presentation) values(#{courseID},#{name},#{presentation})")//插入
    public  boolean insert_course(CourseModel courseModel);

    @Select("select courseID,name,presentation from course where name=#{name}")//通过课程名查找课程
    public CourseModel select_course_from_name(CourseModel courseModel);

    @Select("select courseID,name,presentation from course where courseID=#{courseID}")//通过课程ID查找课程
    public CourseModel select_course_from_courseID(CourseModel courseModel);


    @Select("select * from course")//查询所有课程
    public ArrayList<CourseModel> select_all_course();


    //通过模糊查询通过名字 查询课程编号
    @Select("select * from course where name like '%${name}%' ")
    public ArrayList<CourseModel> select_course_from_name_fuzzy(CourseModel courseModel);




    @Insert("insert into curricula_variable (tno,courseID) values( #{tno},#{courseID})")//插入
    public Boolean insert_curricula_variable(Curricula_variableModel curricula_variableModel);

    @Select("select tno,courseID from curricula_variable where tno=#{tno} and courseID=#{courseID}")//通过教师编号和课程编号查询是否存在
    public Curricula_variableModel select_curricula_variable_from_tno_courseID(Curricula_variableModel curricula_variableModel);

    @Select("select tno,courseID from curricula_variable where tno=#{tno}")//通过教师编号查询课程关系
    public ArrayList<Curricula_variableModel> select_curricula_variable_from_tno(Curricula_variableModel curricula_variableModel);

    @Select("select tno,courseID from curricula_variable where courseID=#{courseID}")//通过课程编号查询课程关系，即一个课程只能复制给一个老师
    public Curricula_variableModel select_curricula_variable_from_courseID(Curricula_variableModel curricula_variableModel);


    //查询某个老师自己的所有课程 todo add
    @Select("select course.courseID,name,course.presentation from course INNER JOIN curricula_variable where tno=#{cv.tno} and course.courseID=curricula_variable.courseID LIMIT #{pl.limit_head},#{pl.limit}")
    public ArrayList<CourseModel> select_course_from_tno(@Param("cv")Curricula_variableModel curricula_variableModel, @Param("pl")pageLimit pl);

    @Select("select count(1) count1 from course INNER JOIN curricula_variable where tno=#{cv.tno} and course.courseID=curricula_variable.courseID")
    public count select_count_course_from_tno(@Param("cv")Curricula_variableModel curricula_variableModel);// todo 加


    //查找某个老师自己的课程 模糊查询 通过课程名 todo add
    @Select("select course.courseID,name,course.presentation from course INNER JOIN curricula_variable where tno=#{curricula_variableModel.tno} and course.courseID=curricula_variable.courseID and course.name like '%${courseModel.name}%' LIMIT #{pl.limit_head},#{pl.limit}")
    public ArrayList<CourseModel> select_course_from_tno_fuzzyCourseName(@Param("courseModel") CourseModel courseModel, @Param("curricula_variableModel") Curricula_variableModel curricula_variableModel, @Param("pl")pageLimit pl);

    @Select("select count(1) count1 from course INNER JOIN curricula_variable where tno=#{curricula_variableModel.tno} and course.courseID=curricula_variable.courseID and course.name like '%${courseModel.name}%'")
    public count select_count_course_from_tno_fuzzyCourseName(@Param("courseModel") CourseModel courseModel, @Param("curricula_variableModel") Curricula_variableModel curricula_variableModel);


    // 这样教师就可以重复利用某课程，通过更改作业的开放时间来设定
    @Update("update course set name=#{name} where courseID=#{courseID}")//更改课程名
    public boolean update_courseName_from_courseID(CourseModel courseModel);

    // 这样教师就可以重复利用某课程，通过更改作业的开放时间来设定
    @Update("update course set presentation=#{presentation} where courseID=#{courseID}")//更改课程简介
    public boolean update_coursePresentation_from_courseID(CourseModel courseModel);


    @Delete("delete from curricula_variable where courseID=#{courseID}")//删除课程关系
    public boolean delete_curricula_variable_from_courseID(Curricula_variableModel curricula_variableModel);

    //删除课程
    @Delete("delete from course where courseID=#{courseID}")
    public boolean delete_course_from_courseID(CourseModel courseModel);


    //新年快乐


}
