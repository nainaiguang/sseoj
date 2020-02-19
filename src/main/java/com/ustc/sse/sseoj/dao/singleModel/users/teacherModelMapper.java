package com.ustc.sse.sseoj.dao.singleModel.users;

import com.ustc.sse.sseoj.model.user.teacherModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface teacherModelMapper {
    int deleteByPrimaryKey(String tno);

    int insert(teacherModel record);

    int insertSelective(teacherModel record);

    teacherModel selectByPrimaryKey(String tno);

    int updateByPrimaryKeySelective(teacherModel record);

    int updateByPrimaryKey(teacherModel record);
}