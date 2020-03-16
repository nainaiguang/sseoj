package com.ustc.sse.sseoj.judgeSystem.judge.module;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.QuestionType;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces.diverterInterface;
import com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces.judgerInterface;
import com.ustc.sse.sseoj.judgeSystem.judge.module.judgeMachine.jugerSimpleFactory;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;
import com.ustc.sse.sseoj.util.ApplicationContextProvider;
import com.ustc.sse.sseoj.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/14 16:14
 * 判题机器
 */
@DependsOn("SpringUtil")
public class judger {
    private jugerSimpleFactory jf;

    private diverterInterface diver;//分流器接口
    private judgerInterface judgering;//判题机器

    public judger()
    {
        jf= SpringUtil.getBean(jugerSimpleFactory.class);
    }

    public Result judging(diverterInterface diver,student_homeworkModelWithBLOBs shmb) {
        this.diver = diver;
        QuestionType questionType=diver.distinguish(shmb);
        /**
         * 运用了简单工厂，这样的话判题不会全部写在这里，增加删除判题只要修改判题工厂中的判断，增加判题的实现类即可，这里不需要进行改动
         */
        this.judgering= jf.createJudger(questionType);
        if(judgering!=null){
        return this.judgering.judge(shmb);}
        else {
            return new Result.Fail(Code.ERROR);
        }
    }

}
