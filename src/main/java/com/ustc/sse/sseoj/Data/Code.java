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

public enum  Code {  // TODO 测试 试试这个在网页中的表示
    TEMP(1,""),
   // public static String
    SUCCESS(0,"success"),
    SUCCESS_CHANGE_NAME(2,"success change name"),
    SUCCESS_ADD_COURSE(10,"success add course"),
    SUCCESS_DETETE_COURSE(11,"success delete course"),
    SUCCESS_DETETE_HOMEWORK(12,"success delete homework"),
    SUCCESS_DETETE_HOMEWORK_LINK(13,"success delete homework link"),
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

    //缺少信息
    MISS_COIRSE_NAME(300," miss course name"),
    MISS_COURSEID(301,"miss courseID"),
    MISS_NEW_CHANGE(302,"miss new change"),
    MISS_HOMEWORKID(303,"miss homeworkID"),
    MISS_HOMEWORKNAME(304,"miss homeworkName"),
    MISS_TNO(310,"miss tno"),
    MISS_ROLE(320,"miss role"),
    MISS_PARAMETE(305,"miss parameter"),
    EMPTY_LIST(306,"empty list"),
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

}
