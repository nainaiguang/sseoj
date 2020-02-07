package com.ustc.sse.sseoj.dao.singleModel.teacher;

import com.ustc.sse.sseoj.model.teacher.course_homeworkModelKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface course_homeworkModelMapper {
    int deleteByPrimaryKey(course_homeworkModelKey key);

    int insert(course_homeworkModelKey record);

    int insertSelective(course_homeworkModelKey record);

    course_homeworkModelKey selectByCourseID(String courseid);
}