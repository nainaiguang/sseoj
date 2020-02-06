package com.ustc.sse.sseoj.dao.singleModel.student;

import com.ustc.sse.sseoj.model.student.student_homeworkModel;
import com.ustc.sse.sseoj.model.student.student_homeworkModelKey;

public interface student_homeworkModelMapper {
    int deleteByPrimaryKey(student_homeworkModelKey key);

    int insert(student_homeworkModel record);

    int insertSelective(student_homeworkModel record);

    student_homeworkModel selectByPrimaryKey(student_homeworkModelKey key);

    int updateByPrimaryKeySelective(student_homeworkModel record);

    int updateByPrimaryKey(student_homeworkModel record);
}