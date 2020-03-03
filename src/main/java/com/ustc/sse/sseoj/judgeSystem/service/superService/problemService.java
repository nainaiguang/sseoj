package com.ustc.sse.sseoj.judgeSystem.service.superService;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.warehouse.questionModel;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 17:22
 */
public interface problemService {
    //往决策里添加问题
    public Result addProblemtojul(questionModel qm);

    //往决策里删除问题 要在自己的问题删除前做这件事
    public Result deleteProblemFromJul(questionModel qm);

    //修改问题情况
    public Result updataProblemFromJul(questionModel qm);


}
