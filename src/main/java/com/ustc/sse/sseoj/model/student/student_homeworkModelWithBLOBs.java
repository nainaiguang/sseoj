package com.ustc.sse.sseoj.model.student;

import org.springframework.context.annotation.Bean;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/9 14:38
 */
public class student_homeworkModelWithBLOBs extends student_homeworkModel {
    private String answers;

    private String error;

    private String runMes;

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers == null ? null : answers.trim();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }

    public String getRunMes() {
        return runMes;
    }

    public void setRunMes(String runMes) {
        this.runMes = runMes == null ? null : runMes.trim();
    }
}