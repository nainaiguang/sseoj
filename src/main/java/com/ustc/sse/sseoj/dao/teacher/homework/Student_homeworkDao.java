package com.ustc.sse.sseoj.dao.teacher.homework;

import com.ustc.sse.sseoj.model.student.student_homeworkModelKey;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/21 17:11
 */
@Mapper
public interface Student_homeworkDao {

    //获取学生某次作业的所有题目
    @Select("SELECT * FROM student_homework " +
            "WHERE sno=#{shmk.sno} AND courseID=#{shmk.courseid} AND homeworkID=#{shmk.homeworkid}")
    public ArrayList<student_homeworkModelWithBLOBs> get_all_question_Homework_student(@Param("shmk") student_homeworkModelKey shmk);
}
