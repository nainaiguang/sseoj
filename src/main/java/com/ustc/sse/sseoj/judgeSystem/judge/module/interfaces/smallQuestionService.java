package com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/15 13:37
 * 缩小版问题服务，为了缩小内存
 */
public interface smallQuestionService {
    //得到某个题目的所有答案
    public Result get_all_answer(questionModel qm);
    //得到某个答案详细信息
    public Result get_answer_detail(answerModel am);
}
