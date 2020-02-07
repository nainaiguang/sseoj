package com.ustc.sse.sseoj.model.teacher;

import lombok.ToString;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
@ToString
public class course_homeworkModelKey {
    private String courseid;

    private String homeworkid;

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
}