package com.ustc.sse.sseoj.dao.singleModel.users;

import com.ustc.sse.sseoj.model.user.studentModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface studentModelMapper {
    int deleteByPrimaryKey(String no);

    int insert(studentModel record);

    int insertSelective(studentModel record);

    studentModel selectByPrimaryKey(String no);

    int updateByPrimaryKeySelective(studentModel record);

    int updateByPrimaryKey(studentModel record);
}