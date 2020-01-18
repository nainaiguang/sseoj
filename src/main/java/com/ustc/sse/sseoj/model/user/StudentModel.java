package com.ustc.sse.sseoj.model.user;

import com.ustc.sse.sseoj.model.user.superUser.UsersModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */
@Getter
@Setter
public class StudentModel extends UsersModel {
    String sno,spassword,sname;
}
