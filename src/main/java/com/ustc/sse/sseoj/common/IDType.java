package com.ustc.sse.sseoj.common;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/20 17:13
 * 需要自己创建id 而不是学校已经有了的id的类型
 * 其中有 课程id CourseID 题目id questionID 作业id homeworkID
 *
 */
public enum  IDType {
    courseID,//课程id
    questionID,//题目id
    homeworkID, //作业id
    answerID,//答案id
    answercaseID //答案案例id
}
