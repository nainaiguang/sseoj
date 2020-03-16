package com.ustc.sse.sseoj.judgeSystem.judge.module;

import com.ustc.sse.sseoj.Data.QuestionType;
import com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces.diverterInterface;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/14 15:50
 * 分流器实现
 */
public class diverter implements diverterInterface {
    @Override
    public QuestionType distinguish(student_homeworkModelWithBLOBs shmb) {
       try {
           if (shmb.getQuestiontype().equals(QuestionType.blank.toString())) {
               return QuestionType.blank;
           } else if (shmb.getQuestiontype().equals(QuestionType.choose.toString())) {
               return QuestionType.choose;
           } else if (shmb.getQuestiontype().equals(QuestionType.programming.toString())) {
               return QuestionType.programming;
           } else {
               return null;
           }
       }
       catch (Exception e)
       {
           return null;
       }

    }
}
