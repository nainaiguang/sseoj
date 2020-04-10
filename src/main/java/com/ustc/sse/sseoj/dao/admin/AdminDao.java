package com.ustc.sse.sseoj.dao.admin;


import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.Curricula_variableModel;
import com.ustc.sse.sseoj.model.user.studentModel;
import com.ustc.sse.sseoj.model.user.uniteModel.teacher_uniteModel;
import com.ustc.sse.sseoj.model.user.uniteModel.student_uniteModel;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;


/**
 * @author 老沙
 * @version 1.0
 * @date 2020/2/15
 */
@Mapper
public interface AdminDao {

    //获取目前的学生中的所有院系
//    @Select("select t1.no,t1.sage,t1.ssex,t1.sgrade,t1.sphone_number,t1.semail,t1.sdept\n" +
//            "FROM student_info  t1,(SELECT student_info.sdept FROM\tstudent_info GROUP BY\tsdept ) t2\n" +
//            "WHERE t1.sdept=t2.sdept\n" +
//            "ORDER BY t1.sdept\n" +
//            "LIMIT #{pl.limit_head},#{pl.limit};")
    @Select("SELECT student_info.sdept FROM student_info GROUP BY sdept LIMIT #{pl.limit_head},#{pl.limit};")
    public ArrayList<String> select_dept_from_students(@Param("pl") pageLimit pl);

    //    @Select("select count(1) count1\n" +
//            "FROM student_info  t1,(SELECT student_info.sdept FROM\tstudent_info GROUP BY\tsdept ) t2\n" +
//            "WHERE t1.sdept=t2.sdept\n" +
//            "ORDER BY \n " +
//            "t1.sdept")
    @Select("SELECT\n" +
            "	count( 1 ) count1 \n" +
            "FROM\n" +
            "	( SELECT student_info.sdept FROM student_info GROUP BY sdept ) t1;")
    public count select_dept_nums_from_students();


    //获取目前的学生中所有年级
//    @Select("select t1.no,t1.sage,t1.ssex,t1.sgrade,t1.sphone_number,t1.semail,t1.sdept\n" +
//            "FROM student_info  t1,(SELECT student_info.sgrade FROM\tstudent_info GROUP BY\tsgrade ) t2\n" +
//            "WHERE t1.sgrade=t2.sgrade\n" +
//            "ORDER BY t1.sgrade\n" +
//            "LIMIT #{pl.limit_head},#{pl.limit};")
    @Select("SELECT student_info.sgrade FROM student_info GROUP BY sgrade LIMIT #{pl.limit_head},#{pl.limit};")
    public ArrayList<String> select_grade_from_students(@Param("pl") pageLimit pl);


    //    @Select("select count(1) count1\n" +
//            "FROM student_info  t1,(SELECT student_info.sgrade FROM\tstudent_info GROUP BY\tsgrade ) t2\n" +
//            "WHERE t1.sgrade=t2.sgrade\n" +
//            "ORDER BY \n" +
//            "t1.sgrade")
    @Select("SELECT\n" +
            "	count( 1 ) count1 \n" +
            "FROM\n" +
            "	( SELECT student_info.sgrade FROM student_info GROUP BY sgrade ) t1;")
    public count select_grade_num_from_students();

    //获取某学生的详细信息
    @Select("SELECT\n" +
            "	student.NO,\n" +
            "	student.NAME,\n" +
            "	student.role,\n" +
            "	student_info.sage,\n" +
            "	student_info.ssex,\n" +
            "	student_info.sgrade,\n" +
            "	student_info.sphone_number,\n" +
            "	student_info.semail,\n" +
            "	student_info.sdept \n" +
            "FROM\n" +
            "	student\n" +
            "	INNER JOIN student_info ON student.NO = student_info.NO \n" +
            "	AND student.NO = #{no};")
    public student_uniteModel select_all_info_from_studentID(String no);

    //跟新该学生的姓名及密码
    @Update("UPDATE student \n" +
            "SET NAME = #{name},\n" +
            "PASSWORD = #{password} \n" +
            "WHERE\n" +
            "	NO = #{name}")
    public int update_nameORpass_from_studentID(studentModel studentModel);

    //跟新该学生所有信息
    @Update("UPDATE student t1\n" +
            "INNER JOIN student_info t2 \n" +
            "SET t1.NAME = #{student_uniteModel.name},\n" +
            "t1.PASSWORD = #{student_uniteModel.password},\n" +
            "t2.sage = #{student_uniteModel.sage},\n" +
            "t2.ssex = #{student_uniteModel.ssex},\n" +
            "t2.sgrade =#{student_uniteModel.sgrade},\n" +
            "t2.sdept = #{student_uniteModel.sdept},\n" +
            "t2.sphone_number = #{student_uniteModel.sphoneNumber},\n" +
            "t2.semail = #{student_uniteModel.semail} \n" +
            "WHERE\n" +
            "	t1.NO = t2.NO \n" +
            "	AND t1.NO = #{student_uniteModel.no};")
    public boolean update_info_from_studentID(@Param("student_uniteModel") student_uniteModel student_uniteModel);

    //获取该学生的所有选课信息
    @Select("SELECT\n" +
            "	t3.courseID,\n" +
            "	t3.NAME,\n" +
            "	t3.presentation \n" +
            "FROM\n" +
            "	student t1,\n" +
            "	select_course t2,\n" +
            "	course t3 \n" +
            "WHERE\n" +
            "	t1.NO = t2.sno \n" +
            "	AND t2.courseID = t3.courseID \n" +
            "	AND t1.NO = #{studentModel.no} \n" +
            "ORDER BY\n" +
            "	t2.courseID \n" +
            "	LIMIT #{pl.limit_head},\n" +
            "	#{pl.limit};")
    public ArrayList<CourseModel> get_courses_from_student(@Param("studentModel") studentModel studentModel, @Param("pl") pageLimit pl);

    @Select("SELECT\n" +
            "	count(1) count1 \n" +
            "FROM\n" +
            "	student t1,\n" +
            "	select_course t2,\n" +
            "	course t3 \n" +
            "WHERE\n" +
            "	t1.NO = t2.sno \n" +
            "	AND t2.courseID = t3.courseID \n" +
            "	AND t1.NO = #{studentModel.no} \n" +
            "ORDER BY\n" +
            "t2.courseID")
    public count get_courses_nums_from_student(@Param("studentModel") studentModel studentModel);


    //删除学生与课程关系
    @Delete("DELETE \n" +
            "FROM\n" +
            "	select_course \n" +
            "WHERE\n" +
            "	sno = #{scmk.sno} \n" +
            "	AND courseID = #{scmk.courseid};")
    public boolean delete_selectCourseModelKey_from_studentID(@Param("scmk") select_courseModelKey scmk);

    //删除学生账号
    @Delete("DELETE student,\n" +
            "student_info \n" +
            "FROM\n" +
            "	student\n" +
            "	INNER JOIN student_info ON student.NO = student_info.NO \n" +
            "WHERE\n" +
            "	student.NO = #{sm.no};")
    public boolean delete_studentInfo_from_studentID(@Param("sm") studentModel sm);


    //获取目前教师中的所有的院系
//    @Select("SELECT\n" +
//            "	t1.NO,\n" +
//            "	t1.tage,\n" +
//            "	t1.tsex,\n" +
//            "	t1.tphone_number,\n" +
//            "	t1.temail,\n" +
//            "	t1.tdept \n" +
//            "FROM\n" +
//            "	teacher_info t1,\n" +
//            "	( SELECT teacher_info.tdept FROM teacher_info GROUP BY tdept ) t2 \n" +
//            "WHERE\n" +
//            "	t1.tdept = t2.tdept \n" +
//            "ORDER BY\n" +
//            "	t1.tdept \n" +
//            "	LIMIT #{pl.limit_head},#{pl.limit};")
//    public ArrayList<teacher_infoModel> select_dept_from_teachers(@Param("pl") pageLimit pl);
    @Select("SELECT teacher_info.tdept FROM teacher_info GROUP BY tdept LIMIT #{pl.limit_head},#{pl.limit};")
    public ArrayList<String> select_dept_from_teachers(@Param("pl") pageLimit pl);

    //    @Select("SELECT count(1) count1\n" +
//            "FROM\n" +
//            "	teacher_info t1,\n" +
//            "	( SELECT teacher_info.tdept FROM teacher_info GROUP BY tdept ) t2 \n" +
//            "WHERE\n" +
//            "	t1.tdept = t2.tdept \n" +
//            "ORDER BY\n" +
//            "	t1.tdept ")
//    public count select_dept_nums_from_teachers();
    @Select("SELECT\n" +
            "	count( 1 ) count1 \n" +
            "FROM\n" +
            "	( SELECT teacher_info.tdept FROM teacher_info GROUP BY tdept ) t1;")
    public count select_dept_nums_from_teachers();


    //获取某位老师的详细信息
    @Select("SELECT\n" +
            "	t1.NO ,\n" +
            "	t1.NAME ,\n" +
            "	t2.tage,\n" +
            "	t2.tsex,\n" +
            "	t2.tphone_number,\n" +
            "	t2.temail,\n" +
            "	t2.tdept,\n" +
            "	t1.role \n" +
            "FROM\n" +
            "	teacher t1\n" +
            "	INNER JOIN teacher_info t2 ON t1.NO = t2.NO \n" +
            "	AND t1.NO = #{no};")
    public teacher_uniteModel select_all_info_from_teacherID(String no);

    //可以某学生进行添加选课
    @Insert("INSERT INTO select_course ( sno, courseID )\n" +
            "VALUES\n" +
            "	( #{scmk.sno}, #{scmk.courseid});")
    public boolean insert_course_for_student(@Param("scmk") select_courseModelKey scmk);

    //删除教师账号
    @Delete("DELETE teacher,\n" +
            "teacher_info \n" +
            "FROM\n" +
            "	teacher\n" +
            "	INNER JOIN teacher_info ON teacher.NO = teacher_info.NO \n" +
            "WHERE\n" +
            "	teacher.NO = #{no};")
    public boolean delete_teacherInfo_from_teacherID(String no);


    //待交作业显示提醒功能



//    //增加教师账号
//    @Select("insert into teacher (no,password,name,role) values(#{no},#{password},#{name},#{role})")
//    public UsersModel teacherRegist(UsersModel user);
//    //搜索教师账号
//    //
//    //增加学生账号
//    @Insert("insert into teacher (sno,spassword,sname) values(#{sno},#{spassword},#{sname})")
//    public  boolean insert_course(StudentModel studentModel);
//    //批量增加学生账号
}
