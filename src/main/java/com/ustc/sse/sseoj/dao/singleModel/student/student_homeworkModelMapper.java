package com.ustc.sse.sseoj.dao.singleModel.student;

import com.ustc.sse.sseoj.model.student.student_homeworkModel;
import com.ustc.sse.sseoj.model.student.student_homeworkModelKey;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/9 14:38
 */
@Mapper
public interface student_homeworkModelMapper {
    int deleteByPrimaryKey(student_homeworkModelKey key);

    int insert(student_homeworkModelWithBLOBs record);

    int insertSelective(student_homeworkModelWithBLOBs record);

    student_homeworkModelWithBLOBs selectByPrimaryKey(student_homeworkModelKey key);

    int updateByPrimaryKeySelective(student_homeworkModelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(student_homeworkModelWithBLOBs record);

    int updateByPrimaryKey(student_homeworkModel record);
}