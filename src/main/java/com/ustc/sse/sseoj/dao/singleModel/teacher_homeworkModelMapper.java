package com.ustc.sse.sseoj.dao.singleModel;

import com.ustc.sse.sseoj.model.teacher.teacher_homeworkModelKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface teacher_homeworkModelMapper {
    int deleteByPrimaryKey(teacher_homeworkModelKey key);

    int insert(teacher_homeworkModelKey record);

    int insertSelective(teacher_homeworkModelKey record);
}