package com.ustc.sse.sseoj.judgeSystem.judge.module.judgeMachine;

import com.ustc.sse.sseoj.Data.QuestionType;
import com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces.judgerInterface;
import com.ustc.sse.sseoj.judgeSystem.judge.module.judgeMachine.judgers.blankJudger;
import com.ustc.sse.sseoj.judgeSystem.judge.module.judgeMachine.judgers.chooseJudger;
import com.ustc.sse.sseoj.judgeSystem.judge.module.judgeMachine.judgers.programmingJudger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/14 16:41
 * 判题器的简单工厂，判断返回哪种判题器
 */

@Service
public class jugerSimpleFactory {
    @Autowired
    blankJudger bj;

    @Autowired
    chooseJudger cj;

    @Autowired
    programmingJudger pj;

    public judgerInterface createJudger(QuestionType questionType)
    {
        try{
            if(questionType.toString().equals(QuestionType.blank.toString()))
            {
                return bj;
            }
            else if (questionType.toString().equals(QuestionType.choose.toString()))
            {
                return cj;
            }
            else if (questionType.toString().equals(QuestionType.programming.toString()))
            {
                return pj;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
