package com.ustc.sse.sseoj.controller.student;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Mes;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.teacher.homework_link_bankModel;
import com.ustc.sse.sseoj.model.user.studentModel;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import com.ustc.sse.sseoj.service.admin.AdminServiceImpl;
import com.ustc.sse.sseoj.service.student.StudentCourseServiceImpl;
import com.ustc.sse.sseoj.service.student.superService.StudentCourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @Description TODO
 * @Author 沙政鑫
 * @Data 2020/2/18  22:58
 */

@Controller
@RequestMapping("/student")
public class StudentCourseController {
    @Autowired
    StudentCourseServiceImpl studentCourseService;



    //获取该学生的所有选课信息及相应教师
    @RequestMapping(value = "/selectCourseInfoFromStudent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes selectCourseInfoFromStudent(pageLimit pl, HttpServletRequest request,String name ){

        studentModel sm = new studentModel();
        UsersModel user = (UsersModel ) request.getSession().getAttribute("user");
        sm.setNo(user.getNo());//通过session获取no
        sm.setName(name);//课程名称，借用name属性

        Result result=studentCourseService.select_courseInfo_from_student(sm,pl);
        Result result1=studentCourseService.select_courseInfo_from_student(sm);

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

    //显示本次作业的所有题目，按题号排序.
    @RequestMapping(value = "/selectQuestionFromHomeworkID", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes selectQuestionFromHomeworkID(pageLimit pl, homeworkModel hm){

        Result result=studentCourseService.select_question_from_homeworkID(hm,pl);
        Result result1=studentCourseService.select_question_from_homeworkID(hm);

        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<homeworkModel> arrayList = (ArrayList<homeworkModel>) ((Result.Success) result).getData();
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

    //显示该课程的所有作业
    @RequestMapping(value = "/selectHomeworkInfoFromCourseID", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes selectHomeworkInfoFromCourseID(pageLimit pl, CourseModel cm){

        Result result=studentCourseService.select_homeworkInfo_from_courseID(cm,pl);
        Result result1=studentCourseService.select_homeworkInfo_from_courseID(cm);

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

    //根据题号获取本次作业的那一题
    @RequestMapping(value = "/selectQuestionFromNumber", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes selectQuestionFromNumber(homework_link_bankModel hlbm){


        Result result=studentCourseService.select_question_from_num(hlbm);


        if(result instanceof Result.Success)
        {
            questionModel questionModel= (questionModel) ((Result.Success) result).getData();

            Mes mes=new Mes(true,Code.SUCCESS,1,questionModel);
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








//    //管理员为学生选课
//    @RequestMapping(value = "/addOneCourseForStudent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    @ResponseBody//返回json格式
//    public Mes add_one_course_for_student(CourseModel courseModel, HttpServletRequest request){
//
//        select_courseModelKey smk=new select_courseModelKey();
//        UsersModel um= (UsersModel) request.getSession().getAttribute("user");
//        smk.setCourseid(courseModel.getCourseID());
//        smk.setSno(um.getNo());
//        Result result=studentCourseService.add_one_course_for_student(smk);
//        if(result instanceof Result.Success){
//            boolean res= (boolean) ((Result.Success) result).getData();
//            Mes mes=new Mes(true, Code.SUCCESS,1,res);
//            return mes;
//        }else if(result instanceof Result.Fail){
//            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
//            return mes;
//        }else {
//            Mes mes=new Mes(false,Code.ERROR,0,null);
//            return mes;
//        }
//    }
//
//    //管理员为学生删除课程
//    @RequestMapping(value = "/deleteOneCourceForStudent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    @ResponseBody//返回json格式
//    public Mes delete_one_cource_for_student(CourseModel courseModel,HttpServletRequest request){
//        select_courseModelKey scmk=new select_courseModelKey();
//        UsersModel um= (UsersModel) request.getSession().getAttribute("user");
//        scmk.setSno(um.getNo());
//        scmk.setCourseid(courseModel.getCourseID());
//        Result result=studentCourseService.delete_one_cource_for_student(scmk);
//        if(result instanceof Result.Success){
//            boolean res= (boolean) ((Result.Success) result).getData();
//            Mes mes=new Mes(true,Code.SUCCESS,1,res);
//            return mes;
//        }else if(result instanceof Result.Fail){
//            Mes mes=new Mes(false,((Result.Fail) result).getReason(),0,null);
//            return mes;
//        }else {
//            Mes mes =new Mes(false,Code.ERROR,0,null);
//            return mes;
//        }
//    }

}
