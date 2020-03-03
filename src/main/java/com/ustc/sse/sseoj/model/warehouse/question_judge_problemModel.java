package com.ustc.sse.sseoj.model.warehouse;

import lombok.ToString;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 14:52
 */
@ToString
public class question_judge_problemModel {
    private String questionid;

    private Integer judgeProblemId;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }

    public Integer getJudgeProblemId() {
        return judgeProblemId;
    }

    public void setJudgeProblemId(Integer judgeProblemId) {
        this.judgeProblemId = judgeProblemId;
    }
}