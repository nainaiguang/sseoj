package com.ustc.sse.sseoj.Data;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/14 19:15
 */
public enum JudgeCode {
    SUMMIT_SUCCESS(000,"summit success"),
    COMMIT_SUCCESS(000,"commit success"),

    RIGHT_ANSWER(001,"right answer"),
    ERROR_ANSWER(101,"error answer"),
    MISS_ANSWER(102,"miss answer"),
    TIME_OUT(103,"time out"),
    MEMORY_EXCESSIVE(104,"memory excessive"),
    RUN_ERROR(105,"run error"),
    COMPILE_ERROR(106,"compile error"),

    SUMMIT_FAIL(107,"summit fail");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    private JudgeCode(int code, String mes) {
        setCode(code);
        setMsg(mes);
    }

    @Override
    public String toString() {
        return "Code["+this.code+","+this.msg+"]";
    }
}
