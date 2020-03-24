package com.ustc.sse.sseoj.judgeSystem.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/22 16:50
 */
@Getter
@Setter
@ToString
public class judgeResultModel extends solutionModel {
    String runtimeinfo_error;
    String compileinfo_error;
}
