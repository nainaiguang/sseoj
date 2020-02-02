package com.ustc.sse.sseoj.service.teacher;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.course_homeworkModelKey;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import com.ustc.sse.sseoj.service.teacher.superService.HomeworkService;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/2 20:06
 */
public class HomeworkServiceImpl implements HomeworkService {
    @Override
    public Result teacher_add_homework(TeacherModel tm, homeworkModel hm, CourseModel cm) {
        return null;
    }

    @Override
    public Result delete_homework(ArrayList<homeworkModel> arrayList) {
        return null;
    }

    @Override
    public Result delete_homework_link_with_course(ArrayList<course_homeworkModelKey> arrayList) {
        return null;
    }

    @Override
    public Result update_homework(homeworkModel hm) {
        return null;
    }

    @Override
    public Result search_homework(TeacherModel tm, homeworkModel hm) {
        return null;
    }
}
