package com.ustc.sse.sseoj.model.student;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
public class select_courseModelKey {
    private String sno;

    private String courseid;

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
}