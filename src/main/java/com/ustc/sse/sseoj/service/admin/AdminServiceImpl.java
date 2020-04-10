package com.ustc.sse.sseoj.service.admin;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.Data.Role;
import com.ustc.sse.sseoj.dao.admin.AdminDao;
import com.ustc.sse.sseoj.dao.admin.student_uniteModelMapper;
import com.ustc.sse.sseoj.dao.admin.teacher_uniteModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.student.select_courseModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.users.adminModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.users.studentModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.users.student_infoModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.users.teacherModelMapper;
import com.ustc.sse.sseoj.dao.teacher.course.courseDAO;

import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.user.*;

import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.model.user.uniteModel.teacher_uniteModel;
import com.ustc.sse.sseoj.model.user.uniteModel.student_uniteModel;
import com.ustc.sse.sseoj.service.admin.superService.AdminService;

import com.ustc.sse.sseoj.util.DefaultPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 * @Description TODO
 * @Author 沙政鑫
 * @Data 2020/2/19  14:01
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired(required = false)
    studentModelMapper smm;
    @Autowired(required = false)
    teacherModelMapper tmm;
    @Autowired(required = false)
    adminModelMapper amm;
    @Autowired(required = false)
    private AdminDao adminDao;
    @Autowired(required = false)
    student_infoModelMapper simm;
    @Autowired(required = false)
    private courseDAO courseado;
    @Autowired(required = false)
    student_uniteModelMapper summ;
    @Autowired(required = false)
    teacher_uniteModelMapper tumm;
    @Autowired(required = false)
    select_courseModelMapper scmm;

    @Override
    public Result select_student_according_condition(student_uniteModel sum,pageLimit pl) {
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        try {
            ArrayList<student_uniteModel> arrayList = summ.selectStudentByChoose(sum,pl);
            return new Result.Success(arrayList);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    @Override
    public Result select_count_according_condition(student_uniteModel sum) {
        try {
            count count1 =summ.selectCountByChoose(sum);
            return new Result.Success(count1);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }


    @Override
    public Result updateSecret(UsersModel um) {
        if(um.getRole()==null){
            return new Result.Fail(Code.MISS_ROLE);
        }
        if(um.getNo()==null){
            return new Result.Fail(Code.MISS_USERSID);
        }
        String password=um.getPassword();
        String no=um.getNo();

        if(password!=null){//用户修改密码
            try {
            if(um.getRole().equals(Role.student.toString())){
                studentModel sm=new studentModel();
                sm.setPassword(password);
                sm.setNo(no);
                smm.updateByPrimaryKeySelective(sm);
            }else if (um.getRole().equals(Role.teacher.toString())){
                System.out.println(no);
                teacherModel tm=new teacherModel();
                tm.setTpassword(password);
                tm.setTno(no);
                tmm.updateByPrimaryKeySelective(tm);
            }else if(um.getRole().equals(Role.manager.toString())){
                adminModel am=new adminModel();
                am.setPassword(password);
                am.setNo(no);
                amm.updateByPrimaryKeySelective(am);
            }else {
                return new Result.Fail(Code.WRONG_ROLE);
            }
            return new Result.Success(true);
            }catch (Exception e) {
                return new Result.Error(e);
            }
        }else {//重置密码
            try{
                String s= DefaultPassword.get_default_Password(um.getNo());
                if(um.getRole().equals(Role.student.toString())){
                    studentModel sm1=new studentModel();
                    sm1.setPassword(s);
                    sm1.setNo(um.getNo());
                    smm.updateByPrimaryKeySelective(sm1);
                }else if (um.getRole().equals(Role.teacher.toString())){
                    teacherModel tm1=new teacherModel();
                    tm1.setTpassword(s);
                    tm1.setTno(um.getNo());
                    tmm.updateByPrimaryKeySelective(tm1);
                }else if(um.getRole().equals(Role.manager.toString())){
                    adminModel am1=new adminModel();
                    am1.setPassword(s);
                    am1.setNo(um.getNo());
                    amm.updateByPrimaryKeySelective(am1);
                }else {
                    return new Result.Fail(Code.WRONG_ROLE);
                }
                 return new Result.Success(true);
            }catch (Exception e) {
                return new Result.Error(e);
            }
        }
    }

    //获取目前的学生中的所有院系
    @Override
    public Result get_all_student_depts(pageLimit pl) {
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        try {
            ArrayList<String> arrayList =adminDao.select_dept_from_students(pl);
            return new Result.Success(arrayList);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result get_all_student_depts() {

        try {
            count count1 =adminDao.select_dept_nums_from_students();
            return new Result.Success(count1);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result get_all_student_grades(pageLimit pl) {
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        try {
            ArrayList<String> arrayList =adminDao.select_grade_from_students(pl);
            return new Result.Success(arrayList);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result get_all_student_grades() {
        try {
            count count1 =adminDao.select_grade_num_from_students();
            return new Result.Success(count1);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }



    @Override
    public Result get_all_info_from_student(studentModel sm) {
        if(sm.getNo()==null){
            return new Result.Fail(Code.MISS_STUDENTID);
        }
        try {
            student_uniteModel sum = adminDao.select_all_info_from_studentID(sm.getNo());
            return new Result.Success(sum);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result update_studentinfo(student_uniteModel sum) {
        if(sum.getNo()==null){
            return new Result.Fail(Code.MISS_STUDENTID);
        }
        try {
            boolean success1=true;
            success1=summ.updateInfoFromStudentID(sum);

            if (success1) {
                return new Result.Success(true);
            } else {
                return new Result.Fail(Code.UNKNOWN_WRONG);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result select_courses_from_student(studentModel sm,pageLimit pl) {
        if(sm.getNo()==null){
            return new Result.Fail(Code.MISS_STUDENTID);
        }
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        try {
            ArrayList<CourseModel> arrayList = adminDao.get_courses_from_student(sm,pl);
            return new Result.Success(arrayList);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result select_courses_from_student(studentModel sm) {
        if(sm.getNo()==null){
            return new Result.Fail(Code.MISS_STUDENTID);
        }

        try {
            count count1  = adminDao.get_courses_nums_from_student(sm);
            return new Result.Success(count1);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result delete_student_courseInfo(select_courseModelKey scmk,studentModel sm) {
        if(sm.getNo()==null)
        {
            return new Result.Fail(Code.MISS_STUDENTID);
        }
        if(scmk.getCourseid()==null)
        {
            return new Result.Fail(Code.MISS_COURSEID);
        }

        try {
            boolean success = adminDao.delete_selectCourseModelKey_from_studentID(scmk,sm);
            if (success) {
                return new Result.Success(true);
            } else {
                return new Result.Fail(Code.UNKNOWN_WRONG);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result delete_branch_student_courseInfo(ArrayList<select_courseModelKey> arrayList,studentModel sm) {
        if(arrayList.size()==0)
        {
            return new Result.Fail(Code.EMPTY_LIST);
        }

        ArrayList<Result> array=new ArrayList<>();
        for(select_courseModelKey select_courseModelKey:arrayList)//检查是否存在空值
        {
            array.add(delete_student_courseInfo(select_courseModelKey,sm));
        }

        return new Result.Success(array);
    }

    @Override
    public Result delete_studentInfo(studentModel sm) {
        if(sm.getNo()==null)
        {
            return new Result.Fail(Code.MISS_STUDENTID);
        }

        try {
            boolean success = adminDao.delete_studentInfo_from_studentID(sm);
            if (success) {
                return new Result.Success(true);
            } else {
                return new Result.Fail(Code.UNKNOWN_WRONG);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result delete_branch_studentInfo(ArrayList<studentModel> arrayList) {
        if(arrayList.size()==0)
        {
            return new Result.Fail(Code.EMPTY_LIST);
        }
        ArrayList<Result> array=new ArrayList<>();
        for(studentModel studentModel:arrayList)//检查是否存在空值
        {
            array.add(delete_studentInfo(studentModel));
        }

        return new Result.Success(array);
    }

    @Override
    public Result select_course_for_student(select_courseModelKey scmk) {
        if(scmk.getSno()==null){
            return new Result.Fail(Code.MISS_STUDENTID);
        }
        if(scmk.getCourseid()==null){
            return new Result.Fail(Code.MISS_COURSEID);
        }

        try {
            int count1 = scmm.insertSelective(scmk);
            if (count1==1) {
                return new Result.Success(true);
            } else {
                return new Result.Fail(Code.UNKNOWN_WRONG);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result select_branchCourse_forStudent(ArrayList<select_courseModelKey> arrayList) {
        if(arrayList.size()==0)
        {
            return new Result.Fail(Code.EMPTY_LIST);
        }
        ArrayList<Result> array=new ArrayList<>();
        for(select_courseModelKey select_courseModelKey:arrayList)//检查是否存在空值
        {
            array.add(select_course_for_student(select_courseModelKey));
        }

        return new Result.Success(array);
    }

    @Override
    public Result select_teacher_according_condition(teacher_uniteModel tum,pageLimit pl) {
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        try {
            ArrayList<teacher_uniteModel> arrayList = tumm.selectTeacherByChoose(tum,pl);
            return new Result.Success(arrayList);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    @Override
    public Result select_count_according_condition(teacher_uniteModel tum) {
        try {
            count count1 =tumm.selectCountByChoose(tum);
            return new Result.Success(count1);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    //获取目前的教师中的所有院系
    @Override
    public Result get_all_teacher_depts(pageLimit pl) {
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        try {

            ArrayList<String> arrayList =adminDao.select_dept_from_teachers(pl);
            return new Result.Success(arrayList);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result get_all_teacher_depts() {

        try {
            count count1 =adminDao.select_dept_nums_from_teachers();
            return new Result.Success(count1);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result get_all_info_from_teacher(teacher_uniteModel tum) {
        if(tum.getNo()==null){
            return new Result.Fail(Code.MISS_TNO);
        }
        try {
            teacher_uniteModel tum1 = adminDao.select_all_info_from_teacherID(tum.getNo());
            return new Result.Success(tum1);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result insert_course_toStudent(select_courseModelKey scmk) {
        if(scmk.getNo()==null){
            return new Result.Fail(Code.MISS_STUDENTID);
        }
        if(scmk.getCourseid()==null){
            return new Result.Fail(Code.MISS_COURSEID);
        }
        try {
            boolean success = adminDao.insert_course_for_student(scmk);
            if (success) {
                return new Result.Success(true);
            } else {
                return new Result.Fail(Code.UNKNOWN_WRONG);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result insert_branch_course_toStudent(ArrayList<select_courseModelKey> arrayList) {
        if(arrayList.size()==0)
        {
            return new Result.Fail(Code.EMPTY_LIST);
        }
        ArrayList<Result> array=new ArrayList<>();
        for(select_courseModelKey scmk:arrayList)//检查是否存在空值
        {
            array.add(insert_course_toStudent(scmk));
        }

        return new Result.Success(array);
    }

    @Override
    public Result delete_teacherInfo(teacherModel tm) {
        if(tm.getTno()==null)
        {
            return new Result.Fail(Code.MISS_TNO);
        }

        try {
            boolean success = adminDao.delete_teacherInfo_from_teacherID(tm.getTno());
            if (success) {
                return new Result.Success(true);
            } else {
                return new Result.Fail(Code.UNKNOWN_WRONG);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result delete_branch_teacherInfo(ArrayList<teacherModel> arrayList) {//teacher与teacher_info必须都存在
        if(arrayList.size()==0)
        {
            return new Result.Fail(Code.EMPTY_LIST);
        }
        ArrayList<Result> array=new ArrayList<>();
        for(teacherModel teacherModel:arrayList)//检查是否存在空值
        {
            array.add(delete_teacherInfo(teacherModel));
        }

        return new Result.Success(array);
    }

    @Override
    public Result update_teacherinfo(teacher_uniteModel tum) {
        if(tum.getNo()==null){
            return new Result.Fail(Code.MISS_TNO);
        }
        try {
            boolean success1=true;
            success1=tumm.updateInfoFromTeacherID(tum);

            if (success1) {
                return new Result.Success(true);
            } else {
                return new Result.Fail(Code.UNKNOWN_WRONG);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

}
