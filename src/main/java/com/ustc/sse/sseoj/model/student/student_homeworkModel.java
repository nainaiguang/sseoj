package com.ustc.sse.sseoj.model.student;

import com.ustc.sse.sseoj.model.student.student_homeworkModelKey;

import java.util.Date;

public class student_homeworkModel extends student_homeworkModelKey {
    private String answerFileLocation;

    private String langauge;

    private Short answerResult;

    private Date sumitTime;

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
}