package com.ustc.sse.sseoj.dao.user;

import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    public List<UsersModel> getUserList();

    @Select("select admin.no,admin.name,admin.role from admin where admin.no=#{no}")
    public UsersModel adminNameByName(@Param("no") String no);

    @Select("select student.no,student.name,student.role from student where student.no=#{no}")
    public UsersModel studentNameByName(@Param("no") String no);

    @Select("select teacher.no,teacher.name,teacher.role from teacher where teacher.no=#{no}")
    public UsersModel teacherNameByName(@Param("no") String no);


    @Select("select admin.no,admin.name,admin.role from admin where admin.no=#{no} and admin.password=#{password}")
    public UsersModel adminLogin(@Param("no") String no, @Param("password") String password);

    @Select("select student.no,student.name,student.role from student where student.no=#{no} and student.password=#{password}")
    public UsersModel studentLogin(@Param("no") String no, @Param("password") String password);

    @Select("select teacher.no,teacher.name,teacher.role from teacher where teacher.no=#{no} and teacher.password=#{password}")
    public UsersModel teacherLogin(@Param("no") String no, @Param("password") String password);


    @Select("insert into admin (no,password,name,role) values(#{no},#{password},#{name},#{role})")
    public UsersModel adminRegist(UsersModel user);

    @Select("insert into student (no,password,name,role) values(#{no},#{password},#{name},#{role})")
    public UsersModel studentRegist(UsersModel user);

    @Select("insert into teacher (no,password,name,role) values(#{no},#{password},#{name},#{role})")
    public UsersModel teacherRegist(UsersModel user);


}
