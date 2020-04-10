package com.ustc.sse.sseoj.model.teacher;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 14:51
 */

@Getter
@ToString
public class Curricula_variableModel {
    @Setter
    private String tno;

    private String courseID;
    private String courseid;

    public void setCourseID(String courseID) {
        this.courseID = courseID;
        this.courseid=courseID;
    }

    public void setCourseid(String courseid)
    {
        this.courseID = courseid;
        this.courseid=courseid;
    }
}
