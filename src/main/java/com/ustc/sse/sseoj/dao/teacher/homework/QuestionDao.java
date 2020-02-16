package com.ustc.sse.sseoj.dao.teacher.homework;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.teacher.homework_link_bankModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/9 16:26
 */
@Mapper
public interface QuestionDao {

    //得到教师的所有问题
    @Select("SELECT\n" +
            "\tquestion.*\n" +
            "FROM\n" +
            "\tquestion\n" +
            "INNER JOIN bank_teacher ON bank_teacher.questionID = question.questionID\n" +
            "WHERE\n" +
            "\tbank_teacher.tno = #{tm.tno}\n" +
            "\tAND question.title LIKE '%${qm.title}%' ")
    public ArrayList<questionModel> get_all_question_from_teacher(@Param("tm") TeacherModel tm,@Param("qm") questionModel qm);

    //得到教师某作业下的所有问题
    @Select("SELECT\n" +
            "\tquestion.*,homework_link_bank.questionNumber\n" +
            "FROM\n" +
            "\tquestion\n" +
            "INNER JOIN homework_link_bank ON question.questionID = homework_link_bank.questionID\n" +
            "INNER JOIN bank_teacher ON bank_teacher.questionID = question.questionID\n" +
            "WHERE\n" +
            "\thomework_link_bank.homeworkID = #{hm.homeworkid}\n" +
            "AND bank_teacher.tno = #{tm.tno}"+
            "\tAND question.title LIKE '%${qm.title}%'\n" +
            "\tORDER BY homework_link_bank.questionNumber")
    public ArrayList<questionModel> get_all_question_from_course_on_teacher(@Param("tm") TeacherModel tm, @Param("hm") homeworkModel hm,@Param("qm") questionModel qm);

    //查询该问题有几个作业在使用
    @Select("SELECT\n" +
            "\thomework.*\n" +
            "FROM\n" +
            "\thomework\n" +
            "INNER JOIN homework_link_bank ON homework.homeworkID = homework_link_bank.homeworkID\n" +
            "INNER JOIN teacher_homework ON teacher_homework.homeworkID = homework.homeworkID\n" +
            "WHERE\n" +
            "\thomework_link_bank.questionID = #{questionid}\n" +
            "AND teacher_homework.tno = #{tno}")
    public ArrayList<homeworkModel> get_all_homework_using_question(String questionid ,String tno);

    //得到某个题目的所有样例
    @Select("SELECT\n" +
            "\tanswer.*\n" +
            "FROM\n" +
            "\tquestion_answer\n" +
            "INNER JOIN answer ON question_answer.answerID = answer.answerID\n" +
            "WHERE\n" +
            "\tquestionID = #{qm.questionid}\n" +
            "AND answer.answer_type = 'cases'")
    public ArrayList<answerModel> get_all_case(@Param("qm") questionModel qm) ;

    //得到某个题目的所有答案
    @Select("SELECT\n" +
            "\tanswer.*\n" +
            "FROM\n" +
            "\tquestion_answer\n" +
            "INNER JOIN answer ON question_answer.answerID = answer.answerID\n" +
            "WHERE\n" +
            "\tquestionID = #{qm.questionid}\n" +
            "AND answer.answer_type = 'answers'")
    public ArrayList<answerModel> get_all_answer(@Param("qm") questionModel qm);

    //搜索该老师的，该作业外的其他题目
    @Select("SELECT\n" +
            "\tquestion.*\n" +
            "FROM\n" +
            "\tbank_teacher\n" +
            "INNER JOIN question ON bank_teacher.questionID = question.questionID\n" +
            "WHERE\n" +
            "\tbank_teacher.tno = #{tm.tno}\n" +
            "AND question.questionID NOT IN ( SELECT\n" +
            "\tquestionID\n" +
            "FROM\n" +
            "\thomework_link_bank\n" +
            "WHERE\n" +
            "\thomework_link_bank.homeworkID = #{hm.homeworkid} )")
    public ArrayList<questionModel> get_question_except_using(@Param("tm") TeacherModel tm, @Param("hm") homeworkModel hm);

    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\thomework_link_bank\n" +
            "WHERE\n" +
            "\thomework_link_bank.homeworkID = #{hlbm.homeworkid}\n" +
            "ORDER BY\n" +
            "\thomework_link_bank.questionNumber")
    public ArrayList<homework_link_bankModel> get_homework_bank_order_questionNumber(@Param("hlbm") homework_link_bankModel hlbm);
}
