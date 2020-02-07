package com.ustc.sse.sseoj.model.teacher;

import lombok.ToString;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
@ToString
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