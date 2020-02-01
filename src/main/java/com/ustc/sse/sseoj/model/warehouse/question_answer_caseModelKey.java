package com.ustc.sse.sseoj.model.warehouse;

public class question_answer_caseModelKey {
    private String questionid;

    private String answercaseid;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }

    public String getAnswercaseid() {
        return answercaseid;
    }

    public void setAnswercaseid(String answercaseid) {
        this.answercaseid = answercaseid == null ? null : answercaseid.trim();
    }
}