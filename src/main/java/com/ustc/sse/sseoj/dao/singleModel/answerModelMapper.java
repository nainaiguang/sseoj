package com.ustc.sse.sseoj.dao.singleModel;

import com.ustc.sse.sseoj.model.warehouse.answerModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface answerModelMapper {
    int deleteByPrimaryKey(String answerid);

    int insert(answerModel record);

    int insertSelective(answerModel record);

    answerModel selectByPrimaryKey(String answerid);

    int updateByPrimaryKeySelective(answerModel record);

    int updateByPrimaryKey(answerModel record);
}