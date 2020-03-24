package com.ustc.sse.sseoj.service.student;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.JudgeCode;
import com.ustc.sse.sseoj.Data.QuestionType;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.singleModel.student.student_homeworkModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.teacher.homeworkModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.warehouse.question_judge_problemModelMapper;
import com.ustc.sse.sseoj.dao.teacher.homework.Student_homeworkDao;
import com.ustc.sse.sseoj.judgeSystem.Model.judgeResultModel;
import com.ustc.sse.sseoj.judgeSystem.judge.interfaces.getJudgeResultFeekback;
import com.ustc.sse.sseoj.judgeSystem.judge.summitBlocksQueue;
import com.ustc.sse.sseoj.model.student.student_homeworkModelKey;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.warehouse.question_judge_problemModel;
import com.ustc.sse.sseoj.service.student.superService.judgeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/23 15:36
 */
@Service
public class judgeQuestionServiceImpl implements judgeQuestionService {
    @Autowired
    homeworkModelMapper hmm;//作业dao

    @Autowired
    student_homeworkModelMapper shmm;//学生写作业的map

    @Autowired
    question_judge_problemModelMapper qjpmm;//查problemid

    @Autowired
    Student_homeworkDao shd;
    //学生提交题目
    @Override
    public Result summitquestion(student_homeworkModelWithBLOBs shmwb) {
        student_homeworkModelKey shmk=new student_homeworkModelKey();

        student_homeworkModelKey wantdelete=null;//准备删除的
        //数据校验
        {
            if(shmwb.getSno()==null)
            {
                return new Result.Fail(Code.MISS_STUDENTID);
            }
            shmk.setSno(shmwb.getSno());
            if(shmwb.getQuestionid()==null)
            {
                return new Result.Fail(Code.MISS_QUESTIONID);
            }
            shmk.setQuestionid(shmwb.getQuestionid());
            if(shmwb.getCourseid()==null)
            {
                return new Result.Fail(Code.MISS_COURSEID);
            }
            shmk.setCourseid(shmwb.getCourseid());
            if(shmwb.getHomeworkid()==null)
            {
                return new Result.Fail(Code.MISS_HOMEWORKID);
            }
            shmk.setHomeworkid(shmwb.getHomeworkid());
            if(shmwb.getQuestiontype()==null)
            {
                return new Result.Fail(Code.MISS_QUESTION_TYPE);
            }
            if(!(shmwb.getQuestiontype().equals(QuestionType.choose.toString())||shmwb.getQuestiontype().equals(QuestionType.blank.toString())||shmwb.getQuestiontype().equals(QuestionType.programming.toString())))
            {
                return new Result.Fail(Code.WRONG_ANSWERTYPE);
            }
            if(shmwb.getAnswers()==null)
            {
                return new Result.Success(true);//答案为空不做处理
            }
        }

        //校验日期和summit
        {
            try {
                homeworkModel hm =hmm.selectByPrimaryKey(shmwb.getHomeworkid());
                Date endTime=hm.getEndtime();
                if(shmwb.getSumitTime().after(endTime))//提交超时
                {
                    return new Result.Fail(Code.OUT_OF_TIME);
                }


                student_homeworkModelWithBLOBs tempshmwb=shmm.selectByPrimaryKey(shmk);
                if(tempshmwb!=null)//原来的不为空
                {
                    if(tempshmwb.getCommit()==1)//已经提交了，不再做验证
                    {
                        return new Result.Fail(Code.DUPUlCATE_SUMMIT);
                    }
                    shmwb.setOldSolutionId(tempshmwb.getSolutionId());//获取原来的solutionid

                    wantdelete=new student_homeworkModelKey();
                    wantdelete.setHomeworkid(tempshmwb.getHomeworkid());
                    wantdelete.setCourseid(tempshmwb.getCourseid());
                    wantdelete.setQuestionid(tempshmwb.getQuestionid());
                    wantdelete.setSno(tempshmwb.getSno());
                }

            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }
        }

        //如果是编程类，语言不可为空
        {
            if(shmwb.getQuestiontype().equals(QuestionType.programming.toString())&&shmwb.getLangauge()==null)
            {
                return new Result.Fail(Code.MISS_LANGUAGE);
            }
        }

        //删除原来的答题，插入新的答题
        {
            try {
                if (wantdelete != null) {
                    shmm.deleteByPrimaryKey(wantdelete);
                }

                shmm.insertSelective(shmwb);//插入新的
            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }
        }

        if(shmwb.getQuestiontype().equals(QuestionType.choose.toString())||shmwb.getQuestiontype().equals(QuestionType.blank.toString()))
        {

            summitBlocksQueue.getInstance().addSummit(shmwb, new getJudgeResultFeekback() {
                @Override
                public void getJudgeResult(Result result) {
                    System.out.println(result);
                    if(result instanceof Result.Success)
                    {
                        JudgeCode rescode= (JudgeCode) ((Result.Success) result).getData();
                        if(rescode.getCode()==JudgeCode.RIGHT_ANSWER.getCode())
                        {
                            shmwb.setAnswerResult((short) 4);
                            shmwb.setError(((JudgeCode) ((Result.Success) result).getData()).getMsg());
                        }
                        else
                        {
                            shmwb.setAnswerResult((short) 22);
                            shmwb.setError(((JudgeCode) ((Result.Success) result).getData()).getMsg());
                        }

                        shmm.updateByPrimaryKeySelective(shmwb);//结果更新入库
                    }
                    else
                    {
                        shmwb.setError(((Result.Fail)result).getReason().getMsg());
                        shmwb.setAnswerResult((short) 20);//4表示正确 //todo 待设定结果代码
                        shmm.updateByPrimaryKeySelective(shmwb);//结果更新入库
                    }
                }
            });
        }
        else //编程题
        {
            try {

                student_homeworkModelWithBLOBs newid= shmm.selectByPrimaryKey(shmk);
                shmwb.setSolutionId(newid.getSolutionId());//设更新后的id

                question_judge_problemModel qjpmres = qjpmm.selectByPrimaryKey(shmwb.getQuestionid());
                shmwb.setProblemId(qjpmres.getJudgeProblemId());

                summitBlocksQueue.getInstance().addSummit(shmwb, new getJudgeResultFeekback() {
                    @Override
                    public void getJudgeResult(Result result) {
                        System.out.println(result);
                        if(result instanceof Result.Success)
                        {
                            if(((Result.Success) result).getData()instanceof JudgeCode)//有代码
                            {
                                shmwb.setAnswerResult((short) 22);
                                shmwb.setError(((JudgeCode) ((Result.Success) result).getData()).getMsg());
                            }
                            else
                            {
                                judgeResultModel jrmres= (judgeResultModel) ((Result.Success) result).getData();
                                if(jrmres!=null)
                                {
                                    shmwb.setAnswerResult((short) jrmres.getResult());
                                    shmwb.setRunMes(jrmres.getRuntimeinfo_error());
                                    shmwb.setError(jrmres.getCompileinfo_error());
                                    shmwb.setRunMemory(jrmres.getMemory());
                                    shmwb.setRunTime(jrmres.getTime());
                                }
                            }

                            shmm.updateByPrimaryKeySelective(shmwb);//结果更新入库
                        }
                        else
                        {
                            shmwb.setError(((Result.Fail)result).getReason().getMsg());
                            shmwb.setAnswerResult((short) 20);//4表示正确 //todo 待设定结果代码
                            shmm.updateByPrimaryKeySelective(shmwb);//结果更新入库
                        }
                    }
                });
            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }

        }

        return new Result.Success(true);
    }

    //学生确认提交作业
    @Override
    public Result confirmCommitHomework(student_homeworkModelKey shmwb) {
        {
            if(shmwb.getSno()==null)
            {
                return new Result.Fail(Code.MISS_STUDENTID);
            }
            if(shmwb.getCourseid()==null)
            {
                return new Result.Fail(Code.MISS_COURSEID);
            }
            if(shmwb.getHomeworkid()==null)
            {
                return new Result.Fail(Code.MISS_HOMEWORKID);
            }
        }
            try {
                homeworkModel hm=hmm.selectByPrimaryKey(shmwb.getHomeworkid());
                Date endtime=hm.getEndtime();//记录结束时间


                ArrayList<student_homeworkModelWithBLOBs> readlyres=shd.get_all_question_Homework_student(shmwb);

                for(student_homeworkModelWithBLOBs temptres:readlyres)
                {
                    if(temptres.getSumitTime().before(endtime))
                    {
                        temptres.setCommit((short) 1);//表示提交
                        shmm.updateByPrimaryKeySelective(temptres);
                    }
                }
                return new Result.Success(true);
            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }



    }
}
