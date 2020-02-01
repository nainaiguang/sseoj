package com.ustc.sse.sseoj.dao.singleModel;

import com.ustc.sse.sseoj.model.user.student_infoModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface student_infoModelMapper {
    int deleteByPrimaryKey(String no);

    int insert(student_infoModel record);

    int insertSelective(student_infoModel record);

    student_infoModel selectByPrimaryKey(String no);

    int updateByPrimaryKeySelective(student_infoModel record);

    int updateByPrimaryKey(student_infoModel record);
}