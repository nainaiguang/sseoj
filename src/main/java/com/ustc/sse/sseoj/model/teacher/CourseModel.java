package com.ustc.sse.sseoj.model.teacher;

import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 13:03
 * 课程model
 */
@Getter
@ToString
public class CourseModel {

   private String courseID;
   private String courseid;
   @Setter
   private String name;
   @Setter
   private String presentation;

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
