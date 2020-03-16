package com.ustc.sse.sseoj.model.student;

import java.util.Date;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/9 14:38
 */
public class student_homeworkModel extends student_homeworkModelKey {
    private String ip;

    private String questiontype;

    private Integer langauge;

    private Short answerResult;

    private Date sumitTime;

    private Short commit;

    private Integer solutionId;

    private Integer runTime;

    private Integer runMemory;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype == null ? null : questiontype.trim();
    }

    public Integer getLangauge() {
        return langauge;
    }

    public void setLangauge(Integer langauge) {
        this.langauge = langauge;
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

    public Short getCommit() {
        return commit;
    }

    public void setCommit(Short commit) {
        this.commit = commit;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public Integer getRunMemory() {
        return runMemory;
    }

    public void setRunMemory(Integer runMemory) {
        this.runMemory = runMemory;
    }
}