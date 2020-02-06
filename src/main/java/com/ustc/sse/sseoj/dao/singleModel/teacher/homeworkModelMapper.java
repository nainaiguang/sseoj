package com.ustc.sse.sseoj.dao.singleModel.teacher;

import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface homeworkModelMapper {
    int deleteByPrimaryKey(String homeworkid);

    int insert(homeworkModel record);

    int insertSelective(homeworkModel record);

    homeworkModel selectByPrimaryKey(String homeworkid);

    int updateByPrimaryKeySelective(homeworkModel record);

    int updateByPrimaryKey(homeworkModel record);
}