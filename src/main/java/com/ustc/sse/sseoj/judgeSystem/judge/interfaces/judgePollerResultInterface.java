package com.ustc.sse.sseoj.judgeSystem.judge.interfaces;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.judgeSystem.Model.judgeResultModel;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/22 21:08
 */
public interface judgePollerResultInterface {
    public void judgePollerResultFeekback(judgeResultModel jrm);
}
