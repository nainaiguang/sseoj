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
@Setter
@ToString
public class CourseModel {

   private String courseID;
   private String name;
   private String presentation;
}
