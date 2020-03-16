package com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/14 16:08
 */
public interface judgerInterface {
    public Result judge(student_homeworkModelWithBLOBs shmb);
}
