package com.ustc.sse.sseoj.model.student;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
public class select_courseModelKey {
    private String sno;

    private String courseid;

    private  String courseID;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
        this.courseid=courseID;
    }

    private String no;

    public String getSno() {
        return sno;
    }

    public String getNo() {
        return no;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
        this.no=sno;
    }

    public void setNo(String no){
        this.no=no;
        this.sno=no;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
        this.courseID=courseid;
    }
}