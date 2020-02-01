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

    //冲突信息
    ACCOUNT_ALREADY_EXISTS(100,"Account already exists"),
    COURSE_ALREADY_EXISTS(110,"course already exists"),


    //不正确信息
    ERROR(200,"error"),
    INCORRECT_ACCOUNT_OR_PASSWORD(201,"Incorrect account or password"),
    WRONG_TNO(210,"wrong tno"),
    WRONG_ROLE(220,"wrong role"),

    //缺少信息
    MISS_COIRSE_NAME(300," miss course name"),
    MISS_COURSEID(301,"miss courseID"),
    MISS_NEW_NAME(302,"miss new name"),
    MISS_TNO(310,"miss tno"),
    MISS_ROLE(320,"miss role"),

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
