package com.ustc.sse.sseoj.dao.singleModel.warehouse;

import com.ustc.sse.sseoj.model.warehouse.questionModel;

public interface questionModelMapper {
    int deleteByPrimaryKey(String questionid);

    int insert(questionModel record);

    int insertSelective(questionModel record);

    questionModel selectByPrimaryKey(String questionid);

    int updateByPrimaryKeySelective(questionModel record);

    int updateByPrimaryKey(questionModel record);
}