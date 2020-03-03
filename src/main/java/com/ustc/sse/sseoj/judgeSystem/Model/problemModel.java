package com.ustc.sse.sseoj.judgeSystem.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 15:46
 */
@Getter
@Setter
@ToString
public class problemModel {
    int problem_id;
    String title;
    double time_limit=-1;//记得把int转double在除1000
    int memory_limit=-1;
}
