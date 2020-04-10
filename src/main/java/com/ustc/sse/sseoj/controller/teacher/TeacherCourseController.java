package com.ustc.sse.sseoj.controller.teacher;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.IDType;
import com.ustc.sse.sseoj.Data.Mes;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.Curricula_variableModel;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.service.admin.AdminServiceImpl;
import com.ustc.sse.sseoj.service.teacher.TeacherCourseServiceImpl;
import com.ustc.sse.sseoj.util.CreatId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/26 17:53
 * 教师课程管理
 */
@Controller
@RequestMapping("/teacher/course")
public class TeacherCourseController<T> {
    @Autowired
    private TeacherCourseServiceImpl teacherCourseService;
    @Autowired
    AdminServiceImpl adminService;

    //获取某个课程详细信息
    @RequestMapping(value = "/searchOneCourseDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes get_one_course_detail(CourseModel courseModel) {
        Result result=teacherCourseService.teacher_search_course_by_courseID(courseModel);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,((CourseModel)((Result.Success) result).getData()));
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

    @RequestMapping(value = "/searchCourse", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes teacher_search_course(pageLimit pl,CourseModel courseModel, HttpServletRequest request) {//改


        if(courseModel.getName()==null)
        {
            return teacher_show_somebody_all_course(pl,request);
        }
        else
        {
            return teacher_search_course_fully(courseModel,pl,request);
        }
    }



    //老师增加课程
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes teacher_add_course(CourseModel courseModel, HttpServletRequest request) {

        Curricula_variableModel curricula_variableModel=new Curricula_variableModel();

        UsersModel user = (UsersModel ) request.getSession().getAttribute("user");
        curricula_variableModel.setTno(user.getNo());//通过session获取no

        Result result=teacherCourseService.teacher_add_course(courseModel,curricula_variableModel);

        if(result instanceof Result.Success)
        {
          CourseModel resCourseModel=(CourseModel)  ((Result.Success) result).getData();
          Mes mes=new Mes(true,Code.SUCCESS_ADD_COURSE,1,resCourseModel);
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

    //根据教师号获取某老师的所有课程
    @RequestMapping(value = "/showAllCourse", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes teacher_show_somebody_all_course(pageLimit pl,HttpServletRequest request) {

        Curricula_variableModel curricula_variableModel=new Curricula_variableModel();

        UsersModel user = (UsersModel ) request.getSession().getAttribute("user");
        curricula_variableModel.setTno(user.getNo());//通过session获取no

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

    //根据课程号修改课程信息
    @RequestMapping(value = "/updateCourse", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes teacher_update_course(CourseModel courseModel) {

        Result result=teacherCourseService.teacher_update_course(courseModel);
        if(result instanceof Result.Success)
        {
            Mes mes=new Mes(true,Code.SUCCESS_DETETE_COURSE,1,courseModel);
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

    //老师增加课程
    @RequestMapping(value = "/deleteBatchCourse", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes teacher_batch_delete_course(@RequestBody ArrayList<CourseModel> arrayList)
    {
        Result result=teacherCourseService.teacher_batch_delete_course(arrayList);

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

    //根据课程号删除课程
    @RequestMapping(value = "/deleteCourse", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes teacher_delete_course(CourseModel courseModel) {
        Result result=teacherCourseService.teacher_delete_course(courseModel);
        if(result instanceof Result.Success)
        {
            Mes mes=new Mes(true,Code.SUCCESS_CHANGE_NAME,1,courseModel);
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

    //根据课程名动态搜索课程课程
    @RequestMapping(value = "/searchCourseFully", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes teacher_search_course_fully(CourseModel courseModel,pageLimit pl, HttpServletRequest request) {

        Curricula_variableModel curricula_variableModel=new Curricula_variableModel();

        UsersModel user = (UsersModel ) request.getSession().getAttribute("user");
        curricula_variableModel.setTno(user.getNo());//通过session获取no

        Result result=teacherCourseService.teacher_search_course_fully(courseModel,curricula_variableModel,pl);
        Result result1=teacherCourseService.teacher_search_count_course_fully(courseModel,curricula_variableModel);//获取总条数
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
    @RequestMapping(value = "/selectBranchCourseForStudent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes selectBranchCourseForStudent(@RequestBody ArrayList<select_courseModelKey> arrayList)
    {

        Result result=adminService.select_branchCourse_forStudent(arrayList);
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
            return new Mes(true,Code.SUCCESS_SELECT_COURSE_,arrayList.size(),null);
        }
        else
        {
            return new Mes(false,Code.ERROR,0,null);
        }
    }

    //根据学号批量删除该学生选课信息
    @RequestMapping(value = "/deleteBranchStudentCourseInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes deleteBranchStudentCourseInfo(@RequestBody ArrayList<select_courseModelKey> arrayList)
    {

        Result result=adminService.delete_branch_student_courseInfo(arrayList);
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

}
