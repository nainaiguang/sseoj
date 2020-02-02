package com.ustc.sse.sseoj.model.user.superUser;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */
@Getter
@Setter
public class UsersModel implements Serializable {
private int id;
private String role;
private boolean successLogin;
private boolean successRegister;
private String no,password,name;
}
