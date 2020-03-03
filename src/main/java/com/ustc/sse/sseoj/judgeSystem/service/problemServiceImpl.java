package com.ustc.sse.sseoj.judgeSystem.service;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.QuestionType;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.singleModel.warehouse.question_judge_problemModelMapper;
import com.ustc.sse.sseoj.judgeSystem.Model.problemModel;
import com.ustc.sse.sseoj.judgeSystem.dao.problemDao;
import com.ustc.sse.sseoj.judgeSystem.fileManager.ftpManager;
import com.ustc.sse.sseoj.judgeSystem.service.superService.problemService;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import com.ustc.sse.sseoj.model.warehouse.question_judge_problemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 16:51
 */
@Service
public class problemServiceImpl implements problemService {
    @Autowired
    question_judge_problemModelMapper qjpmm;


    @Override
    public Result addProblemtojul(questionModel qm) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        if(qm.getTitle()==null)
        {
            return new Result.Fail(Code.MISS_QUESTION_TITLE);
        }
        if(qm.getQuestiontype()==null)
        {
            return new Result.Fail(Code.MISS_QUESTION_TYPE);
        }
        if(!qm.getQuestiontype().equals(QuestionType.programming.toString()))
        {
            return new Result.Success(true);
        }
        //判断完题目类型，才能判断空间时间限制
        if(qm.getTimelimit()==null)
        {
            return new Result.Fail(Code.MISS_TIME_LIMIT);
        }
        if(qm.getMemorylimit()==null)
        {
            return new Result.Fail(Code.MISS_MEMORY_LIMIT);
        }

        try{
            question_judge_problemModel qjmbefore=new question_judge_problemModel();
            qjmbefore.setQuestionid(qm.getQuestionid());
            qjpmm.insertSelective(qjmbefore);
            question_judge_problemModel qjmafter= qjpmm.selectByPrimaryKey(qm.getQuestionid());

            problemModel pm=new problemModel();
            pm.setProblem_id(qjmafter.getJudgeProblemId());
            pm.setMemory_limit(qm.getMemorylimit());
            pm.setTitle(qm.getTitle());
            pm.setTime_limit(qm.getTimelimit()/1000.0);

            int res=new problemDao().insert(pm);
            if(res==0)
            {
                return new Result.Fail(Code.ERROR);
            }

            //创建文件夹
            ftpManager fma=new ftpManager();
            fma.createDir(pm.getProblem_id()+"/");//文件夹名字为自增id加/

            fma.closeFtpConnect();
            return new Result.Success(true);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }



    }

    //往决策里删除问题 todo 要在自己的问题删除前做这件事
    @Override
    public Result deleteProblemFromJul(questionModel qm) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        if(!qm.getQuestiontype().equals(QuestionType.programming.toString()))
        {
            return new Result.Success(true);
        }
        try{
            question_judge_problemModel qjmafter= qjpmm.selectByPrimaryKey(qm.getQuestionid());//查询问题在jul中的id
            int id=qjmafter.getJudgeProblemId();
            problemModel pm=new problemModel();
            pm.setProblem_id(id);
            boolean res=new problemDao().delete(pm);
            if(res==true)
            {
                return new Result.Fail(Code.ERROR);
            }

            ftpManager fmp=new ftpManager();
            fmp.deleteDirAndAllFile(pm.getProblem_id()+"/");

            fmp.closeFtpConnect();

            return new Result.Success(true);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    //更新jul中的问题
    @Override
    public Result updataProblemFromJul(questionModel qm) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        if(!(qm.getTitle()!=null||qm.getTimelimit()!=-1||qm.getMemorylimit()!=-1))//全部为不改变的值
        {
            return  new Result.Success(true);
        }

        try{
            question_judge_problemModel qjmafter= qjpmm.selectByPrimaryKey(qm.getQuestionid());//查询问题在jul中的id
            int id=qjmafter.getJudgeProblemId();
            problemModel pm=new problemModel();
            pm.setProblem_id(id);

            pm.setTitle(qm.getTitle());
            pm.setMemory_limit(qm.getMemorylimit());

            if(qm.getTimelimit()!=-1)
            {
                pm.setTime_limit(qm.getTimelimit()/1000.0);
            }


            problemDao pdao=new problemDao();
            int a=pdao.update(pm);
            if(a==0)
            {
                return new Result.Fail(Code.ERROR);
            }
            else
            {
                return new Result.Success(true);
            }

        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }




}
