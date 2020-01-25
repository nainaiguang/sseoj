package com.ustc.sse.sseoj.service.teacher.superService;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.Curricula_variableModel;
import org.springframework.stereotype.Service;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 16:01
 */
public interface CourseService {
    public Result teacher_add_course(CourseModel courseModel, Curricula_variableModel curricula_variableModel);
}
