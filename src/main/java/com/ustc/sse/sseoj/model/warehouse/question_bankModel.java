package com.ustc.sse.sseoj.model.warehouse;

public class question_bankModel {
    private String questionid;

    private String question;

    private String annotation;

    private Integer timelimit;

    private Integer memorylimit;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation == null ? null : annotation.trim();
    }

    public Integer getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(Integer timelimit) {
        this.timelimit = timelimit;
    }

    public Integer getMemorylimit() {
        return memorylimit;
    }

    public void setMemorylimit(Integer memorylimit) {
        this.memorylimit = memorylimit;
    }
}