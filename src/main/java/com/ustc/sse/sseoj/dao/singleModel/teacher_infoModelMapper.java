package com.ustc.sse.sseoj.dao.singleModel;

import com.ustc.sse.sseoj.model.user.teacher_infoModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface teacher_infoModelMapper {
    int deleteByPrimaryKey(String no);

    int insert(teacher_infoModel record);

    int insertSelective(teacher_infoModel record);

    teacher_infoModel selectByPrimaryKey(String no);

    int updateByPrimaryKeySelective(teacher_infoModel record);

    int updateByPrimaryKey(teacher_infoModel record);
}