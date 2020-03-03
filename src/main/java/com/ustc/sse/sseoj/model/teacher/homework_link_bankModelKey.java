package com.ustc.sse.sseoj.model.teacher;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 14:52
 */
public class homework_link_bankModelKey {
    private String homeworkid;

    private String questionid;

    public String getHomeworkid() {
        return homeworkid;
    }

    public void setHomeworkid(String homeworkid) {
        this.homeworkid = homeworkid == null ? null : homeworkid.trim();
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }
}