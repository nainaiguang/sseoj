package com.ustc.sse.sseoj.model.warehouse;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
public class question_answerModelKey {
    private String questionid;

    private String answerid;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }

    public String getAnswerid() {
        return answerid;
    }

    public void setAnswerid(String answerid) {
        this.answerid = answerid == null ? null : answerid.trim();
    }
}