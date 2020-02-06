package com.ustc.sse.sseoj.dao.singleModel.warehouse;

import com.ustc.sse.sseoj.model.warehouse.questionModel;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
public interface questionModelMapper {
    int deleteByPrimaryKey(String questionid);

    int insert(questionModel record);

    int insertSelective(questionModel record);

    questionModel selectByPrimaryKey(String questionid);

    int updateByPrimaryKeySelective(questionModel record);

    int updateByPrimaryKey(questionModel record);
}