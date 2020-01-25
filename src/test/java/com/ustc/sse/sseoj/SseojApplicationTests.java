package com.ustc.sse.sseoj;

import com.ustc.sse.sseoj.dao.teacher.course.courseDAO;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.Curricula_variableModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SseojApplicationTests {

    @Autowired
    courseDAO add_courseDAO;
    @Test
    void contextLoads() {

        Curricula_variableModel curricula_variableModel=new Curricula_variableModel();

        curricula_variableModel.setTno("SA19225052");
        CourseModel courseModel=new CourseModel();
        courseModel.setName("数学");
        courseModel.setCourseID("12");
//        ArrayList<CourseModel> list=add_courseDAO.select_course_from_tno_fuzzyCourseName(courseModel,curricula_variableModel);
//
//        for(CourseModel cv:list)
//        {
//            System.out.println(cv.getCourseID()+":"+cv.getName());
//        }
        add_courseDAO.delete_course_from_courseID(courseModel);
    }

}
