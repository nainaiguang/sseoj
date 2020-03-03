package com.ustc.sse.sseoj.judgeSystem.service.superService;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/2 18:29
 */
public interface answerService {

    //增加或修改答案或案例
    public Result addOrUpdateAnswer(questionModel qm,answerModel am);


    //删除答案
    public Result deleteAnswer(questionModel qm,answerModel am);

}
