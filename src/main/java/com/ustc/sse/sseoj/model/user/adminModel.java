package com.ustc.sse.sseoj.model.user;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 14:52
 */
public class adminModel {
    private String no;

    private String password;

    private String name;

    private String role;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
}