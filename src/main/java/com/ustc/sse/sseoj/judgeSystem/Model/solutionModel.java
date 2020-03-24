package com.ustc.sse.sseoj.judgeSystem.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/21 16:00
 */
@ToString
@Getter
@Setter
public class solutionModel {
    int solution_id;
    int problem_id;
    String user_id="admin";
    int time;
    int memory;
    String in_date;
    int result=0;
    int language;
    String ip="127.0.0.1";
}
