package com.ustc.sse.sseoj.judgeSystem.judge.module.judgeMachine.judgers;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces.judgerInterface;
import com.ustc.sse.sseoj.judgeSystem.judge.module.smallQuestionServiceImpl;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import com.ustc.sse.sseoj.util.ApplicationContextProvider;
import com.ustc.sse.sseoj.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.ustc.sse.sseoj.Data.JudgeCode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/14 16:39
 * 填空判断器  判题归判题，不对数据库进行插入操作，保证功能唯一性
 */
@Component
public class blankJudger implements judgerInterface {

    @Autowired
    public smallQuestionServiceImpl sqsi;

    @Override
    public Result judge(student_homeworkModelWithBLOBs shmb) {
        System.out.println("填空题判题中");
        //sqsi= ApplicationContextProvider.getBean(smallQuestionServiceImpl.class);

        String questionid=shmb.getQuestionid();
        questionModel qm=new questionModel();
        qm.setQuestionid(questionid);

        Result res=sqsi.get_all_answer(qm);

        if(res instanceof Result.Success)
        {
            ArrayList<answerModel> reslist= (ArrayList<answerModel>) ((Result.Success) res).getData();
            if(reslist.size()==0)
            {
                return new Result.Fail(Code.Question_Miss_ANSWER);
            }
            else {
                for (answerModel resam : reslist)//填空题只要第一个答案
                {
                    if (resam.getInput().equals(shmb.getAnswers())) {
                        return new Result.Success(JudgeCode.RIGHT_ANSWER);//结果正确
                    } else {
                        return new Result.Success(JudgeCode.ERROR_ANSWER);//结果错误
                    }
                }
                return new Result.Fail(Code.Question_Miss_ANSWER);
            }
        }
        else
        {
            return res;//转发失败结果
        }



    }
}
