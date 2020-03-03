package com.ustc.sse.sseoj.dao.singleModel.student;

import com.ustc.sse.sseoj.model.student.student_homeworkModel;
import com.ustc.sse.sseoj.model.student.student_homeworkModelKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 14:52
 */
@Mapper
public interface student_homeworkModelMapper {
    int deleteByPrimaryKey(student_homeworkModelKey key);

    int insert(student_homeworkModel record);

    int insertSelective(student_homeworkModel record);

    student_homeworkModel selectByPrimaryKey(student_homeworkModelKey key);

    int updateByPrimaryKeySelective(student_homeworkModel record);

    int updateByPrimaryKey(student_homeworkModel record);
}