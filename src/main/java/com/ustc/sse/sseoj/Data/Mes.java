package com.ustc.sse.sseoj.Data;

import lombok.*;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/26 17:19
 * 前端消息类
 */

@NoArgsConstructor
@ToString
public class Mes<T> {
    @Getter
    @Setter
    boolean success;//是否成功
    @Getter
    int code;//代码
    @Getter
    String msg;//消息
    @Getter
    @Setter
    T data;//数据

    public Mes(boolean success, Code code, T data) {
        this.success = success;
        this.data = data;
        setCode(code);
    }

    public void setCode(Code code) {
        this.code = code.getCode();
        msg=code.getMsg();
    }
}
