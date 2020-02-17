package com.ustc.sse.sseoj.controller.teacher;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Mes;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.course_homeworkModelKey;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.service.teacher.HomeworkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
@Controller
@RequestMapping("/teacher/course/homework")
public class TeacherHomeworkController {

    @Autowired
    HomeworkServiceImpl homeworkService;

    //获取某个作业详细信息
    @RequestMapping(value = "/searchOneHomeworkDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes get_one_homework_detail(homeworkModel hm)
    {
        Result result=homeworkService.get_one_homework_detail(hm);
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
    //教师添加作业
    //其中 如果作业本身不存在，则创建作业，否则不创建
    // 且课程id不存在，则添加作业，连接教师与作业
    //如果课程id存在，则添加作业，连接教师与作业，连接课程与作业
    //需要 homeworkID/homeworkName,courseID,  该参数名不代表真实参数名，使用请查阅相应的model
    //courseID,homeworkID  连接课程与作业
    //name 添加作业，连接教师与作业
    //name，courseID 添加作业，连接教师与作业,连接课程与作业
    @RequestMapping(value = "/addHomework", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes teacher_add_homework(homeworkModel hm, CourseModel cm, HttpServletRequest request)
    {
        cm.setName(null);//用不上起矛盾

        TeacherModel tm=new TeacherModel();

        UsersModel user= (UsersModel) request.getSession().getAttribute("user");
        tm.setTno(user.getNo());


        Result res=homeworkService.teacher_add_homework(tm,hm,cm);
        if(res instanceof Result.Success)
        {
            Mes mes=new Mes(true,Code.SUCCESS,1,((Result.Success) res).getData());
            return mes;
        }
        else if(res instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) res).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            System.out.println(res.toString());
            return getErrorMes(res);
        }
    }

    //教师删除作业，直接删除，包括批量 homeworkid
    @RequestMapping(value = "/deleteHomework", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes delete_homework(@RequestBody ArrayList<homeworkModel> arrayList)
    {

        Result res=homeworkService.delete_homework(arrayList);
        if(res instanceof Result.Success)
        {
            ArrayList<Result> ar=(ArrayList<Result>) ((Result.Success) res).getData();
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
                return new Mes(true,Code.SUCCESS_DETETE_HOMEWORK,0,null);
            }
            else
            {
                return new Mes(false,Code.ERROR,0,null);
            }
        }
        else if(res instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) res).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            return getErrorMes(res);
        }

    }

    //删除课程与作业的关系，包括批量
    @RequestMapping(value = "/deleteHomeworkLinkCourse", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes delete_homework_link_with_course(@RequestBody ArrayList<course_homeworkModelKey> arrayList)
    {
        System.out.println(arrayList);
        Result res=homeworkService.delete_homework_link_with_course(arrayList);
        if(res instanceof Result.Success)
        {
            ArrayList<Result> ar=(ArrayList<Result>) ((Result.Success) res).getData();
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
                return new Mes(true,Code.SUCCESS_DETETE_HOMEWORK_LINK,0,null);
            }
            else
            {
                return new Mes(false,Code.ERROR,0,null);
            }
        }
        else if(res instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) res).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            return getErrorMes(res);
        }
    }

    //更新作业信息，其中包括 名称，描述，开始时间，结束时间
    @RequestMapping(value = "/updateHomework", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes update_homework(homeworkModel hm)
    {
        Result res=homeworkService.update_homework(hm);
        if(res instanceof Result.Success)
        {
            Mes mes=new Mes(true,Code.SUCCESS,1,((Result.Success) res).getData());
            return mes;
        }
        else if(res instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) res).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            return getErrorMes(res);
        }
    }


    //显示某老师的作业课程，包括模糊查询
    //courseID|""  name!""
    @RequestMapping(value = "/searchHomework", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes search_homework(CourseModel cm, homeworkModel hm, pageLimit pl,HttpServletRequest request)
    {
        TeacherModel tm=new TeacherModel();

            UsersModel user= (UsersModel) request.getSession().getAttribute("user");
            tm.setTno(user.getNo());

        Result res=homeworkService.search_homework(tm,cm,hm,pl);
        if(res instanceof Result.Success)
        {
            ArrayList<homeworkModel> ar=(ArrayList<homeworkModel>) ((Result.Success) res).getData();
            return new Mes(true,Code.SUCCESS,ar.size(),ar);
        }
        else if(res instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) res).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            System.out.println(res.toString());
            return getErrorMes(res);
        }
    }

    //显示这个作业哪几门在用
    @RequestMapping(value = "/searchHomeworkUsing", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes people_use_homework(homeworkModel hm)
    {
        Result res=homeworkService.people_use_homework(hm);
        if(res instanceof Result.Success)
        {
            ArrayList<CourseModel> ar=(ArrayList<CourseModel>) ((Result.Success) res).getData();
            return new Mes(true,Code.SUCCESS,ar.size(),ar);
        }
        else if(res instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) res).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            return getErrorMes(res);
        }
    }

    //显示目前属于该教师，但没有在该课程下的所有作业
    @RequestMapping(value = "/searchHomeworkWithoutUsing", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes search_homework_without_using(CourseModel cm,homeworkModel hm ,pageLimit pl,HttpServletRequest request)
    {
        TeacherModel tm=new TeacherModel();

        UsersModel user= (UsersModel) request.getSession().getAttribute("user");
        tm.setTno(user.getNo());

        Result res=homeworkService.search_homework_without_using(tm,cm,hm,pl);
        if(res instanceof Result.Success)
        {
            ArrayList<homeworkModel> ar=(ArrayList<homeworkModel>) ((Result.Success) res).getData();
            return new Mes(true,Code.SUCCESS,ar.size(),ar);
        }
        else if(res instanceof Result.Fail)
        {
            Mes mes=new Mes(false,((Result.Fail) res).getReason(),0,null);
            System.out.println(mes.toString());
            return mes;
        }
        else
        {
            return getErrorMes(res);
        }
    }

    private Mes getErrorMes(Result result)
    {
        Mes mes=new Mes(false,Code.ERROR,0,null);
        System.out.println(mes.toString());
        return mes;
    }

}
