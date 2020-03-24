package com.ustc.sse.sseoj.service.student.superService;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.student.student_homeworkModelKey;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;

import java.util.Date;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/23 15:01
 */
public interface judgeQuestionService {

    //学生提交题目
    public Result summitquestion(student_homeworkModelWithBLOBs shmwb);

    //学生确认提交作业
    public Result confirmCommitHomework(student_homeworkModelKey shmwb);
}
