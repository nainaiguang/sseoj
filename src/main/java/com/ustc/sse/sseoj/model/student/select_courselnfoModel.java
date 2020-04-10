package com.ustc.sse.sseoj.model.student;

/**
 * @Description TODO
 * @Author 沙政鑫
 * @Data 2020/3/10  14:22
 */
public class select_courselnfoModel {
    private String courseID;
    private String courseid;
    private String name;
    private String presentation;
    private String teacherName;

    public String getCourseid()
    {
        return courseid;
    }
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
        this.courseid=courseID;
    }
    public void setCourseid(String courseid)
    {
        this.courseID = courseid;
        this.courseid=courseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "select_courselnfoModel{" +
                "courseID='" + courseID + '\'' +
                ", name='" + name + '\'' +
                ", presentation='" + presentation + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
