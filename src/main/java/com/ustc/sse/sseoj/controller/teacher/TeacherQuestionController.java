package com.ustc.sse.sseoj.controller.teacher;


import com.ustc.sse.sseoj.Data.*;
import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.teacher.homework_link_bankModel;
import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import com.ustc.sse.sseoj.model.user.teacherModel;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import com.ustc.sse.sseoj.service.teacher.QuestionServiceImpl;
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
 * @date 2020/2/13 10:14
 */
@Controller
@RequestMapping("/teacher/course/question")
public class TeacherQuestionController {
    @Autowired
    QuestionServiceImpl qsimpl;

    //添加问题（包括作业与问题的关系），但添加教师关系
    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes add_question(homeworkModel hm, questionModel qm, HttpServletRequest request){
        teacherModel tm=new teacherModel();
        UsersModel user= (UsersModel) request.getSession().getAttribute("user");
        tm.setTno(user.getNo());

        Result result=qsimpl.add_question(tm,hm,qm);

        if(result instanceof Result.Success)
        {
            return new Mes(true, Code.SUCCESS,1,true);
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

    //添加问题与作业关系
    @RequestMapping(value = "/addHomeworkQuestionRelationship", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes add_relationship_homework_question(homework_link_bankModel hlbm, HttpServletRequest request){
        //todo look record
        teacherModel tm=new teacherModel();
        UsersModel user= (UsersModel) request.getSession().getAttribute("user");
        tm.setTno(user.getNo());

        Result result=qsimpl.add_relationship_homework_question(hlbm,tm);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,true);
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

    //获取该教师的，某作业的所有题目（包括模糊）
    @RequestMapping(value = "/getAllQuestionFromHomework", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public  Mes get_all_question_from_homework(homeworkModel hm,questionModel qm,pageLimit pl ,HttpServletRequest request){
        teacherModel tm=new teacherModel();
        UsersModel user= (UsersModel) request.getSession().getAttribute("user");
        tm.setTno(user.getNo());
        Result result=qsimpl.get_all_question_from_homework(tm,hm,qm,pl);
        Result result1=qsimpl.get_all_question_count_from_homework(tm,hm,qm);
        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<questionModel> arrayList=((ArrayList<questionModel>)((Result.Success) result).getData());
            count count1= (count) ((Result.Success) result1).getData();
            return new Mes(true,Code.SUCCESS,count1.getCount1(),arrayList);
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

    //搜索题目,该老师的（包括模糊搜索，根据题目名）
    @RequestMapping(value = "/searchQuestion", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes search_question(questionModel qm, pageLimit pl ,HttpServletRequest request){
        teacherModel tm=new teacherModel();
        UsersModel user= (UsersModel) request.getSession().getAttribute("user");
        tm.setTno(user.getNo());

        Result result=qsimpl.search_question(tm,qm,pl);
        Result result1=qsimpl.search_question_count(tm,qm);

        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<questionModel> reslist=((ArrayList<questionModel>)((Result.Success) result).getData());
            count count1= (count) ((Result.Success) result1).getData();
            return new Mes(true,Code.SUCCESS,count1.getCount1(),reslist);
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

    //更新问题
    @RequestMapping(value = "/updateQuestion", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes update_question(questionModel qm){
        Result result=qsimpl.update_question(qm);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,true);
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

    //删除问题与作业关系
    @RequestMapping(value = "/deleteHomeworkQuestionRelationship", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes delete_relationship_homework_question(homework_link_bankModel hlbm){
        Result result=qsimpl.delete_relationship_homework_question(hlbm);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,true);
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

    //删除问题
    @RequestMapping(value = "/deleteQuestion", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes delete_question(questionModel qm){
        Result result=qsimpl.delete_question(qm);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,true);
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

    //搜索该题目几个作业在使用
    @RequestMapping(value = "/getHomeworkUseQuestion", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes get_homework_use_question(questionModel qm,HttpServletRequest request){
        teacherModel tm=new teacherModel();
        UsersModel user= (UsersModel) request.getSession().getAttribute("user");
        tm.setTno(user.getNo());
        Result result= qsimpl.get_homework_use_question(qm,tm);

        if(result instanceof Result.Success)
        {
            ArrayList<homeworkModel> arrayRes=((ArrayList<homeworkModel>)((Result.Success) result).getData());
            return new Mes(true,Code.SUCCESS,arrayRes.size(),arrayRes);
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

    //得到某个题目详细信息
    @RequestMapping(value = "/getQuestionDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes get_question_detail(questionModel qm){
        Result result=qsimpl.get_question_detail(qm);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,((questionModel)((Result.Success) result).getData()));
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


    //搜索该老师的，该作业外的其他题目,包括模糊
    @RequestMapping(value = "/getQuestionExceptUsing", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes get_question_except_using(homeworkModel hm,pageLimit pl ,HttpServletRequest request){
        teacherModel tm=new teacherModel();
        UsersModel user= (UsersModel) request.getSession().getAttribute("user");
        tm.setTno(user.getNo());

        Result result= qsimpl.get_question_except_using(tm,hm,pl);
        Result result1=qsimpl.get_question_count_except_using(tm,hm);

        if(result instanceof Result.Success && result1 instanceof Result.Success)
        {
            ArrayList<questionModel> arrayRes=(ArrayList<questionModel>)((Result.Success) result).getData();
            count count1= (count) ((Result.Success) result1).getData();
            return new Mes(true,Code.SUCCESS,count1.getCount1(),arrayRes);
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

    //刷新某次作业的所有题号
    @RequestMapping(value = "/ReflashQuestionNumber", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes reflash_questionNumber(homework_link_bankModel hlbm)
    {
        Result result=qsimpl.reflash_questionNumber(hlbm);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,true);
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

    //批量更改问题的题号
    @RequestMapping(value = "/updateQuestionNumberBatch", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes updateQuestionNumberBatch(@RequestBody ArrayList<homework_link_bankModel> arrayList)
    {
        Result result=qsimpl.updateQuestionNumberBatch(arrayList);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,true);
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

    //得到某个答案详细信息
    @RequestMapping(value = "/getAnswerDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes get_answer_detail(answerModel am){
        Result result=qsimpl.get_answer_detail(am);
        if(result instanceof Result.Success)
        {

            return new Mes(true,Code.SUCCESS,1,((answerModel)((Result.Success) result).getData()));
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


    //得到某个题目的所有样例或答案
    @RequestMapping(value = "/getAllAnswerOrCase", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes get_all_answer_or_case(questionModel qm,answerModel am){
        if(am.getAnswerType().equals(AnswerType.answers.toString()))
        {
            return get_all_answer(qm);
        }
        else if(am.getAnswerType().equals(AnswerType.cases.toString()))
        {
            return get_all_case(qm);
        }
        else
        {
            return new Mes(false,Code.WRONG_ANSWERTYPE,0,null);
        }
    }

    //得到某个题目的所有样例
    //得到某个题目的所有样例或答案
    @RequestMapping(value = "/getAllCase", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    private   Mes get_all_case(questionModel qm){
        Result result=qsimpl.get_all_case(qm);
        if(result instanceof Result.Success)
        {
            ArrayList<answerModel> reslist=((ArrayList<answerModel>)((Result.Success) result).getData());
            return new Mes(true,Code.SUCCESS,reslist.size(),reslist);
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

    //得到某个题目的所有答案
    //得到某个题目的所有样例或答案
    @RequestMapping(value = "/getAllAnswer", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    private Mes get_all_answer(questionModel qm){
        Result result=qsimpl.get_all_answer(qm);
        if(result instanceof Result.Success)
        {
            ArrayList<answerModel> reslist=((ArrayList<answerModel>)((Result.Success) result).getData());
            return new Mes(true,Code.SUCCESS,reslist.size(),reslist);
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

    //添加答案 案例（连接题目与答案）
    @RequestMapping(value = "/addAnswerOrCase", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes add_answer_or_case(questionModel qm,answerModel am){
        Result result=qsimpl.add_answer_or_case(qm,am);
        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,true);
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

    //修改答案
    @RequestMapping(value = "/updateAnswerOrCase", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public  Mes update_answer_or_case(answerModel am){
        Result result=qsimpl.update_answer_or_case(am);

        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,true);
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

    //删除答案
    @RequestMapping(value = "/deleteAnswerOrCase", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody//返回json格式
    public Mes  delete_answer_or_case(answerModel am){
        Result result=qsimpl.delete_answer_or_case(am);

        if(result instanceof Result.Success)
        {
            return new Mes(true,Code.SUCCESS,1,true);
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
