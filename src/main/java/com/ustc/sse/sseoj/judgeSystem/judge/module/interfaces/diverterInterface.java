package com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces;

import com.ustc.sse.sseoj.Data.QuestionType;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/14 15:48
 * 三种题型的分流器接口
 */
public interface diverterInterface {
    abstract public QuestionType distinguish(student_homeworkModelWithBLOBs shmb);
}
