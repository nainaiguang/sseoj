package com.ustc.sse.sseoj.dao.singleModel.warehouse;

import com.ustc.sse.sseoj.model.warehouse.question_answerModelKey;

public interface question_answerModelMapper {
    int deleteByPrimaryKey(question_answerModelKey key);

    int insert(question_answerModelKey record);

    int insertSelective(question_answerModelKey record);
}