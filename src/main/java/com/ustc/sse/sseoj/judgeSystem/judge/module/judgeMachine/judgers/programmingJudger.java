package com.ustc.sse.sseoj.judgeSystem.judge.module.judgeMachine.judgers;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces.judgerInterface;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;
import org.springframework.stereotype.Component;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/14 16:40
 * 编程判题器
 */
@Component
public class programmingJudger implements judgerInterface {
    @Override
    public Result judge(student_homeworkModelWithBLOBs shmb) {
        System.out.println("编程题判题中");
        return null;
    }
}
