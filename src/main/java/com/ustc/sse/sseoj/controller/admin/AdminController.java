package com.ustc.sse.sseoj.controller.admin;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Mes;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.Curricula_variableModel;
import com.ustc.sse.sseoj.model.user.studentModel;
import com.ustc.sse.sseoj.model.user.student_infoModel;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.model.user.teacherModel;
import com.ustc.sse.sseoj.model.user.uniteModel.teacher_uniteModel;
import com.ustc.sse.sseoj.model.user.uniteModel.student_uniteModel;
import com.ustc.sse.sseoj.service.admin.AdminServiceImpl;
import com.ustc.sse.sseoj.service.teacher.TeacherCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @Description TODO
 * @Author 沙政鑫
 * @Data 2020/2/19  14:40
 */
@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private TeacherCourseServiceImpl teacherCourseService;



    //获取满足查询要求的所有学生(可以根据院系，年级来筛选,名字)
    @RequestMapping(value = "/selectStudentAccordingCondition", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes selectStudentAccordingCondition(student_uniteModel student_uniteModel,pageLimit pl){
        Result result=adminService.select_student_according_condition(student_uniteModel,pl);
        Result result1 = adminService.select_count_according_condition(student_uniteModel);
        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<student_uniteModel> arrayList= (ArrayList<student_uniteModel>) ((Result.Success) result).getData();
            count count2= (count) ((Result.Success) result1).getData();
            Mes mes=new Mes(true,Code.SUCCESS,count2.getCount1(),arrayList);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }
    }


    //获取满足查询要求的所有教师(可以根据模糊姓名，教师号，年龄，性别，模糊电话号码，模糊电子邮件，院系查询)
    @RequestMapping(value = "/selectTeacherAccordingCondition", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes selectTeacherAccordingCondition(teacher_uniteModel teacher_uniteModel,pageLimit pl){
        Result result=adminService.select_teacher_according_condition(teacher_uniteModel,pl);
        Result result1 = adminService.select_count_according_condition(teacher_uniteModel);
        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<teacher_uniteModel> arrayList= (ArrayList<teacher_uniteModel>) ((Result.Success) result).getData();
            count count2= (count) ((Result.Success) result1).getData();
            Mes mes=new Mes(true,Code.SUCCESS,count2.getCount1(),arrayList);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }
    }


    //重置及修改学生、老师及管理员密码(待测）
    @RequestMapping(value = "/updateSecret", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes updateSecret(UsersModel um, HttpServletRequest request){
        if(um.getNo()==null){
            UsersModel um1= (UsersModel) request.getSession().getAttribute("user");
            um.setNo(um1.getNo());
        }
        Result result = adminService.updateSecret(um);
        if(result instanceof Result.Success){
            boolean res= (boolean) ((Result.Success) result).getData();
            Mes mes=new Mes(true, Code.SUCCESS,1,res);
            return mes;
        }else if(result instanceof Result.Fail){
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            return mes;
        }else {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            return mes;
        }
    }

    //获取目前的学生中的所有院系
    @RequestMapping(value = "/getAllStudentDepts", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes getAllStudentDepts(pageLimit pl){
        Result result=adminService.get_all_student_depts(pl);
        Result result1 = adminService.get_all_student_depts();
        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<String> arrayList= (ArrayList<String>) ((Result.Success) result).getData();
            count count2= (count) ((Result.Success) result1).getData();
            Mes mes=new Mes(true,Code.SUCCESS,count2.getCount1(),arrayList);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }
    }

    //获取目前的学生中所有年级
    @RequestMapping(value = "/getAllStudentGrades", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes getAllStudentGrades(pageLimit pl){
        Result result=adminService.get_all_student_grades(pl);
        Result result1 = adminService.get_all_student_grades();
        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<String> arrayList= (ArrayList<String>) ((Result.Success) result).getData();
            count count2= (count) ((Result.Success) result1).getData();
            Mes mes=new Mes(true,Code.SUCCESS,count2.getCount1(),arrayList);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }
    }

    //获取某学生的详细信息
    @RequestMapping(value = "/getAllStudentInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes getAllStudentInfo (studentModel sm){
        Result result = adminService.get_all_info_from_student(sm);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,((student_uniteModel)((Result.Success) result).getData()));
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }
    }

    //根据学号修改该学生信息
    @RequestMapping(value = "/updateStudentInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes updateStudentInfo (student_uniteModel sum){
//
//        if(sum.getNo()==null){
//            UsersModel um1= (UsersModel) request.getSession().getAttribute("user");
//            sum.setNo(um1.getNo());
//        }

        Result result=adminService.update_studentinfo(sum);
        if(result instanceof Result.Success)
        {
            Mes mes=new Mes(true,Code.SUCCESS_DELETE_STUDENTINFO,1,sum);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }

    }

   // 根据学号获取该学生的所有选课信息
    @RequestMapping(value = "/selectCourseFromStudent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes selectCourseFromStudent(pageLimit pl,studentModel sm ){


//        studentModel sm = new studentModel();
//
//        UsersModel user = (UsersModel ) request.getSession().getAttribute("user");
//        sm.setNo(user.getNo());//通过session获取no

        Result result=adminService.select_courses_from_student(sm,pl);
        Result result1=adminService.select_courses_from_student(sm);

        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<CourseModel> arrayList= (ArrayList<CourseModel>) ((Result.Success) result).getData();
            count count1= (count) ((Result.Success) result1).getData();
            Mes mes=new Mes(true,Code.SUCCESS,count1.getCount1(),arrayList);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }
    }


    //根据学号批量删除该学生选课信息
    @RequestMapping(value = "/deleteBranchStudentCourseInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes deleteBranchStudentCourseInfo(@RequestBody ArrayList<select_courseModelKey> arrayList,studentModel sm)
    {

        Result result=adminService.delete_branch_student_courseInfo(arrayList,sm);
        ArrayList<Result> ar=(ArrayList<Result>) ((Result.Success) result).getData();
        boolean success=true;//是否成功
        for(Result result1:ar)
        {
            if(result1 instanceof Result.Success)
            {
                continue;
            }
            else
            {
                success=false;
            }
        }
        if(success)
        {
            return new Mes(true,Code.SUCCESS_DETETE_COURSE,0,null);
        }
        else
        {
            return new Mes(false,Code.ERROR,0,null);
        }
    }

    //批量为学生选课
//    @RequestMapping(value = "/selectBranchCourseForStudent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    @ResponseBody//返回json格式
//    public Mes selectBranchCourseForStudent(@RequestBody ArrayList<select_courseModelKey> arrayList)
//    {
//
//        Result result=adminService.select_branchCourse_forStudent(arrayList);
//        ArrayList<Result> ar=(ArrayList<Result>) ((Result.Success) result).getData();
//        boolean success=true;//是否成功
//        for(Result result1:ar)
//        {
//            if(result1 instanceof Result.Success)
//            {
//                continue;
//            }
//            else
//            {
//                success=false;
//            }
//        }
//        if(success)
//        {
//            return new Mes(true,Code.SUCCESS_SELECT_COURSE_,arrayList.size(),null);
//        }
//        else
//        {
//            return new Mes(false,Code.ERROR,0,null);
//        }
//    }


    //根据学号批量删除学生信息
    @RequestMapping(value = "/deleteBranchStudentInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes deleteBranchStudentInfo(@RequestBody ArrayList<studentModel> arrayList)
    {

        Result result=adminService.delete_branch_studentInfo(arrayList);
        ArrayList<Result> ar=(ArrayList<Result>) ((Result.Success) result).getData();
        boolean success=true;//是否成功
        for(Result result1:ar)
        {
            if(result1 instanceof Result.Success)
            {
                continue;
            }
            else
            {
                success=false;
            }
        }
        if(success)
        {
            return new Mes(true,Code.SUCCESS_DETETE_COURSE,0,null);
        }
        else
        {
            return new Mes(false,Code.ERROR,0,null);
        }
    }


    //获取目前的教师中的所有院系
    @RequestMapping(value = "/getAllTeachersDepts", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes getAllTeachersDepts(pageLimit pl){
        Result result=adminService.get_all_teacher_depts(pl);
        Result result1 = adminService.get_all_teacher_depts();
        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<student_infoModel> arrayList= (ArrayList<student_infoModel>) ((Result.Success) result).getData();
            count count2= (count) ((Result.Success) result1).getData();
            Mes mes=new Mes(true,Code.SUCCESS,count2.getCount1(),arrayList);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }
    }


    //根据教师号获取某教师的详细信息
    @RequestMapping(value = "/getAllTeacherInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes getAllTeacherInfo (teacher_uniteModel tum){
        Result result = adminService.get_all_info_from_teacher(tum);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,((teacher_uniteModel)((Result.Success) result).getData()));
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }
    }


    //根据教师号获取某老师的所有课程
    @RequestMapping(value = "/showAllCourse", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes teacher_show_somebody_all_course(pageLimit pl,Curricula_variableModel curricula_variableModel) {

//        Curricula_variableModel curricula_variableModel=new Curricula_variableModel();

//        UsersModel user = (UsersModel ) request.getSession().getAttribute("user");
//        curricula_variableModel.setTno(user.getNo());//通过session获取no

        Result result=teacherCourseService.teacher_show_somebody_all_course(curricula_variableModel,pl);
        Result result1=teacherCourseService.teacher_count_somebody_all_course(curricula_variableModel);
        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<CourseModel> arrayList= (ArrayList<CourseModel>) ((Result.Success) result).getData();
            count count1= (count) ((Result.Success) result1).getData();
            Mes mes=new Mes(true,Code.SUCCESS,count1.getCount1(),arrayList);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }
    }

    //批量为学生选课
    @RequestMapping(value = "/insertBranchStudentCourseInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes insertBranchStudentCourseInfo(@RequestBody ArrayList<select_courseModelKey> arrayList,CourseModel cm)
    {

        Result result=adminService.insert_branch_course_toStudent(arrayList,cm);
        ArrayList<Result> ar=(ArrayList<Result>) ((Result.Success) result).getData();
        boolean success=true;//是否成功
        for(Result result1:ar)
        {
            if(result1 instanceof Result.Success)
            {
                continue;
            }
            else
            {
                success=false;
            }
        }
        if(success)
        {
            return new Mes(true,Code.SUCCESS_INSERT_COURSE,0,null);
        }
        else
        {
            return new Mes(false,Code.ERROR,0,null);
        }
    }

    //批量删除教师信息
    @RequestMapping(value = "/deleteBranchTeacherInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes deleteBranchTeacherInfo(@RequestBody ArrayList<teacherModel> arrayList)
    {

        Result result=adminService.delete_branch_teacherInfo(arrayList);
        ArrayList<Result> ar=(ArrayList<Result>) ((Result.Success) result).getData();
        boolean success=true;//是否成功
        for(Result result1:ar)
        {
            if(result1 instanceof Result.Success)
            {
                continue;
            }
            else
            {
                success=false;
            }
        }
        if(success)
        {
            return new Mes(true,Code.SUCCESS_DELETE_TEACHERINFO,0,null);
        }
        else
        {
            return new Mes(false,Code.ERROR,0,null);
        }
    }

    //根据学号修改该教师信息
    @RequestMapping(value = "/updateTeacherInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes updateTeacherInfo (teacher_uniteModel tum){

        Result result=adminService.update_teacherinfo(tum);
        if(result instanceof Result.Success)
        {
            Mes mes=new Mes(true,Code.SUCCESS_DELETE_STUDENTINFO,1,tum);
            System.out.println(mes.toString());
            return mes;
        }
        else if(result instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            Mes mes=new Mes(false,Code.ERROR,0,null);
            System.out.println(mes.toString());
            return mes;
        }

    }
}
