package com.ustc.sse.sseoj.model.teacher;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
public class bank_teacherModelKey {
    private String tno;

    private String questionid;

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno == null ? null : tno.trim();
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }
}