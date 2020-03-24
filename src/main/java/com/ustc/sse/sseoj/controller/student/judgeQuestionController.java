package com.ustc.sse.sseoj.controller.student;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.JudgeCode;
import com.ustc.sse.sseoj.Data.Mes;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.student.student_homeworkModelKey;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.service.student.judgeQuestionServiceImpl;
import com.ustc.sse.sseoj.util.getIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/23 21:41
 */
@Controller
@RequestMapping("/student")
public class judgeQuestionController {

    @Autowired
    judgeQuestionServiceImpl jqsi;

    //学生提交题目到判题系统
    @RequestMapping(value = "/judgeQuestion", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes selectCourseInfoFromStudent(student_homeworkModelWithBLOBs shmwb, HttpServletRequest request) {
        shmwb.setSumitTime(new Date());//设置提交时间

        String ip= getIp.getUserIP(request);//设置ip
        shmwb.setIp(ip);

        UsersModel user = (UsersModel ) request.getSession().getAttribute("user");
        shmwb.setSno(user.getNo());//通过session获取no

        Result result=jqsi.summitquestion(shmwb);

        if(result instanceof Result.Success)
        {
            Mes mes=new Mes(true, Code.SUCCESS,0, JudgeCode.SUMMIT_SUCCESS);
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

    //学生确认提交作业
    @RequestMapping(value = "/commitHomework", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes confirmCommitHomework(student_homeworkModelKey shmk,HttpServletRequest request)
    {
        UsersModel user = (UsersModel ) request.getSession().getAttribute("user");
        shmk.setSno(user.getNo());

        Result result=jqsi.confirmCommitHomework(shmk);
        if(result instanceof Result.Success)
        {
            Mes mes=new Mes(true, Code.SUCCESS,0, JudgeCode.COMMIT_SUCCESS);
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
