package com.ustc.sse.sseoj.model.student;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 14:52
 */
public class student_homeworkModelKey {
    private String sno;

    private String courseid;

    private String homeworkid;

    private String questionid;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

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