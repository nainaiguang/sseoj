package com.ustc.sse.sseoj.dao.singleModel.warehouse;

import com.ustc.sse.sseoj.model.warehouse.answerModel;

public interface answerModelMapper {
    int deleteByPrimaryKey(String answerid);

    int insert(answerModel record);

    int insertSelective(answerModel record);

    answerModel selectByPrimaryKey(String answerid);

    int updateByPrimaryKeySelective(answerModel record);

    int updateByPrimaryKey(answerModel record);
}