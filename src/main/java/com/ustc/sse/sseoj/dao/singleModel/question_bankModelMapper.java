package com.ustc.sse.sseoj.dao.singleModel;

import com.ustc.sse.sseoj.model.warehouse.question_bankModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface question_bankModelMapper {
    int deleteByPrimaryKey(String questionid);

    int insert(question_bankModel record);

    int insertSelective(question_bankModel record);

    question_bankModel selectByPrimaryKey(String questionid);

    int updateByPrimaryKeySelective(question_bankModel record);

    int updateByPrimaryKey(question_bankModel record);
}