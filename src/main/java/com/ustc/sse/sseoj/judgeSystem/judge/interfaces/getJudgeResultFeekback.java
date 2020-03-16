package com.ustc.sse.sseoj.judgeSystem.judge.interfaces;

import com.ustc.sse.sseoj.Data.Result;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/10 20:14
 * 用来回调判题结果
 */
public interface getJudgeResultFeekback {
    public abstract void getJudgeResult(Result result);
}
