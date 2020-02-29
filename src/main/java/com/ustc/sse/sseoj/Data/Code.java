package com.ustc.sse.sseoj.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/27 16:55
 * 错误代码合集
 */

public enum  Code {
    TEMP(1,""),
   // public static String
    SUCCESS(0,"success"),
    SUCCESS_CHANGE_NAME(2,"success change name"),
    SUCCESS_ADD_COURSE(10,"success add course"),
    SUCCESS_DETETE_COURSE(11,"success delete course"),
    SUCCESS_DETETE_HOMEWORK(12,"success delete homework"),
    SUCCESS_DETETE_HOMEWORK_LINK(13,"success delete homework link"),
 SUCCESS_DELETE_STUDENTINFO(14,"success delete studentInfo"),
 SUCCESS_INSERT_COURSE(15,"success insert course"),
 SUCCESS_DELETE_TEACHERINFO(16,"success delete teacherInfo"),
    //冲突信息
    ACCOUNT_ALREADY_EXISTS(100,"Account already exists"),
    COURSE_ALREADY_EXISTS(110,"course already exists"),


    //不正确信息
    ERROR(200,"error"),
    INCORRECT_ACCOUNT_OR_PASSWORD(201,"Incorrect account or password"),
    WRONG_TNO(210,"wrong tno"),
    WRONG_ROLE(220,"wrong role"),
    WRONG_COURSEID(211,"wrong courseID"),
    WRONG_HOMEWORKID(212,"wrong homeworkID"),
    WRONG_PARAMETER(201,"wrong parameter"),
    WRONG_ANSWERTYPE(213,"wrong answer type"),

    //缺少信息
    MISS_COIRSE_NAME(300," miss course name"),
    MISS_COURSEID(301,"miss courseID"),
    MISS_NEW_CHANGE(302,"miss new change"),
    MISS_HOMEWORKID(303,"miss homeworkID"),
    MISS_HOMEWORKNAME(304,"miss homeworkName"),
    MISS_QUESTIONID(305,"miss questionID"),
    MISS_ANSWERID(306,"miss answerID"),
    MISS_TNO(310,"miss tno"),
    MISS_ROLE(320,"miss role"),
    MISS_PARAMETE(307,"miss parameter"),
    EMPTY_LIST(308,"empty list"),
    Miss_QUESTIONNUMBER(309,"miss question number"),

    MISS_QUESTION_TYPE(330,"miss question type"),
    MISS_QUESTION_TITLE(331,"miss question title"),
    MISS_QUESTION_DESCRIPTION(332,"miss question description"),

    MISS_PAGE_NUMBER(333,"miss page number"),
    MISS_PAGE_LIMIT(334,"miss page limit"),


 MISS_STUDENTID(350,"miss studentID"),
 MISS_USERSID(351,"miss usersID"),
 MISS_STUDENT_GRADE(352,"miss studentGrade"),
 MISS_STUDENT_DEPT(353,"miss studentDept"),


   //未知错误
   UNKNOWN_WRONG(999,"unknown wrong");


    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    private Code(int code, String mes) {
        setCode(code);
        setMsg(mes);
    }

   @Override
   public String toString() {
       return "Code["+this.code+","+this.msg+"]";
   }
}
