package com.ustc.sse.sseoj.model.student;

import java.util.Date;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 14:52
 */
public class student_homeworkModel extends student_homeworkModelKey {
    private String answers;

    private String answerFileLocation;

    private String langauge;

    private Short answerResult;

    private Date sumitTime;

    private Integer solutionId;

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers == null ? null : answers.trim();
    }

    public String getAnswerFileLocation() {
        return answerFileLocation;
    }

    public void setAnswerFileLocation(String answerFileLocation) {
        this.answerFileLocation = answerFileLocation == null ? null : answerFileLocation.trim();
    }

    public String getLangauge() {
        return langauge;
    }

    public void setLangauge(String langauge) {
        this.langauge = langauge == null ? null : langauge.trim();
    }

    public Short getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(Short answerResult) {
        this.answerResult = answerResult;
    }

    public Date getSumitTime() {
        return sumitTime;
    }

    public void setSumitTime(Date sumitTime) {
        this.sumitTime = sumitTime;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }
}