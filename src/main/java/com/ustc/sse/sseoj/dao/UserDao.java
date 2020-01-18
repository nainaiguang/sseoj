package com.ustc.sse.sseoj.dao;

import com.ustc.sse.sseoj.model.user.AdminModel;
import com.ustc.sse.sseoj.model.user.StudentModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */

@Mapper
public interface UserDao {
    // MyBatis 的注解
    @Select("select * from admin")
    public List<AdminModel> getUserList();

    @Select("select admin.ano,admin.aname,admin.role from admin where admin.ano=#{ano}")
    public AdminModel adminNameByName(String ano);

    @Select("select student.sno,student.sname,student.role from student where student.sno=#{sno}")
    public StudentModel studentNameByName(String sno);

    @Select("select teacher.tno,teacher.tname,teacher.role from teacher where teacher.tno=#{tno}")
    public TeacherModel teacherNameByName(String tno);


    @Select("select admin.ano,admin.aname,admin.role from admin where admin.ano=#{ano} and admin.apassword=#{apassword}")
    public AdminModel adminLogin(String ano, String apassword);

    @Select("select student.sno,student.sname,student.role from student where student.sno=#{sno} and student.spassword=#{spassword}")
    public StudentModel studentLogin(String sno,String spassword);

    @Select("select teacher.tno,teacher.tname,teacher.role from teacher where teacher.tno=#{tno} and teacher.tpassword=#{tpassword}")
    public TeacherModel teacherLogin(String tno,String tpassword);


    @Select("insert into admin (ano,apassword,aname,role) values(#{ano},#{apassword},#{aname},#{role})")
    public AdminModel adminRegist(AdminModel user);

    @Select("insert into student (sno,spassword,sname,role) values(#{sno},#{spassword},#{sname},#{role})\"")
    public StudentModel studentRegist(StudentModel user);

    @Select("insert into teacher (tno,tpassword,tname,role) values(#{tno},#{tpassword},#{tname},#{role})\"")
    public TeacherModel teacherRegist(TeacherModel user);

}
