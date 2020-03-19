package com.ustc.sse.sseoj.dao.student;


import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.student.display_questionNum;
import com.ustc.sse.sseoj.model.student.select_courselnfoModel;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.teacher.homework_link_bankModel;
import com.ustc.sse.sseoj.model.user.studentModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * @author 老沙
 * @version 1.0
 * @date 2020/3/10
 */
@Mapper
public interface StudentDao {

    //获取该学生的所有选课信息及相应教师
    @Select("SELECT\n" +
            "	t3.courseID,\n" +
            "	t3.NAME,\n" +
            "	t3.presentation,\n" +
            "	t5.NAME teacherName \n" +
            "FROM\n" +
            "	student t1,\n" +
            "	select_course t2,\n" +
            "	course t3,\n" +
            "	curricula_variable t4,\n" +
            "	teacher t5 \n" +
            "WHERE\n" +
            "	t1.NO = t2.sno \n" +
            "	AND t2.courseID = t3.courseID \n" +
            "	AND t2.courseID = t4.courseID \n" +
            "	AND t4.tno = t5.NO \n" +
            "	AND t1.NO =#{studentModel.no}\n" +
            "	AND t3.name LIKE '%${studentModel.name}%'\n" +
            "ORDER BY\n" +
            "	t2.courseID \n" +
            "	LIMIT #{pl.limit_head},\n" +
            "	#{pl.limit};")
    public ArrayList<select_courselnfoModel> get_coursesInfo_from_student(@Param("studentModel") studentModel studentModel, @Param("pl") pageLimit pl);

    @Select("SELECT\n" +
            "	count(1) count1 \n" +
            "FROM\n" +
            "	student t1,\n" +
            "	select_course t2,\n" +
            "	course t3,\n" +
            "	curricula_variable t4,\n" +
            "	teacher t5 \n" +
            "WHERE\n" +
            "	t1.NO = t2.sno \n" +
            "	AND t2.courseID = t3.courseID \n" +
            "	AND t2.courseID = t4.courseID \n" +
            "	AND t4.tno = t5.NO \n" +
            "	AND t1.NO =#{studentModel.no}\n" +
            "ORDER BY\n" +
            "	t2.courseID ")
    public count get_coursesInfo_nums_from_student(@Param("studentModel") studentModel studentModel);


    //显示该课程的所有作业
    @Select("SELECT\n" +
            "	t2.* \n" +
            "FROM\n" +
            "	course_homework t1\n" +
            "	INNER JOIN homework t2 ON t1.homeworkID = t2.homeworkID \n" +
            "WHERE\n" +
            "	t1.courseID = #{courseModel.courseID} \n" +
            "	AND t2.name LIKE '%${courseModel.name}%' \n" +
            "	AND t2.beginTime < NOW( ) \n" +
            "GROUP BY\n" +
            "	endTime < now( ),\n" +
            "IF\n" +
            "	( endTime < now( ), 0, endTime ),\n" +
            "	endTime DESC \n" +
            "	LIMIT #{pl.limit_head},\n" +
            "	#{pl.limit};")
    public ArrayList<homeworkModel> get_homeworkInfo_from_courseID(@Param("courseModel")CourseModel courseModel, @Param("pl") pageLimit pl);

    @Select("SELECT\n" +
            "	count(1) count1 \n" +
            "FROM\n" +
            "	course_homework t1\n" +
            "	INNER JOIN homework t2 ON t1.homeworkID = t2.homeworkID \n" +
            "WHERE\n" +
            "	t1.courseID = #{courseModel.courseID} \n" +
            "	AND t2.beginTime < NOW( ) ")
    public count get_homeworkInfo_nums_from_courseID(@Param("courseModel")CourseModel courseModel);

    //显示本次作业的所有题目，按题号排序.
    @Select("SELECT\n" +
            "t1.questionNumber,\n" +
            "	t2.* \n" +
            "FROM\n" +
            "	homework_link_bank t1\n" +
            "	INNER JOIN question t2 ON t1.questionID = t2.questionID \n" +
            "WHERE\n" +
            "	t1.homeworkID = #{homeworkModel.homeworkid} \n" +
            "GROUP BY\n" +
            "	t1.questionNumber \n" +
            "	LIMIT #{pl.limit_head},\n" +
            "	#{pl.limit};")
    public ArrayList<display_questionNum> get_question_from_homeworkID(@Param("homeworkModel") homeworkModel homeworkModel, @Param("pl") pageLimit pl );

    @Select("SELECT\n" +
            "count(1) count1 \n" +
            "FROM\n" +
            "	homework_link_bank t1\n" +
            "	INNER JOIN question t2 ON t1.questionID = t2.questionID \n" +
            "WHERE\n" +
            "	t1.homeworkID = #{homeworkModel.homeworkid} ")
    public count get_question_nums_from_homeworkID(@Param("homeworkModel") homeworkModel homeworkModel);

    //根据题号获取本次作业的那一题
    @Select("SELECT\n" +
            "	t2.* \n" +
            "FROM\n" +
            "	homework_link_bank t1\n" +
            "	INNER JOIN question t2 ON t1.questionID = t2.questionID \n" +
            "WHERE\n" +
            "	t1.questionNumber = #{homework_link_bankModel.questionnumber} \n" +
            "	AND t1.homeworkID = #{homework_link_bankModel.homeworkid};")
    public questionModel get_question_from_num(@Param("homework_link_bankModel") homework_link_bankModel homework_link_bankModel);

}
