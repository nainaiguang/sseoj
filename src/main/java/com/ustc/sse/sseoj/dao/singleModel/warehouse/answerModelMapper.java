package com.ustc.sse.sseoj.dao.singleModel.warehouse;

import com.ustc.sse.sseoj.model.warehouse.answerModel;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
public interface answerModelMapper {
    int deleteByPrimaryKey(String answerid);

    int insert(answerModel record);

    int insertSelective(answerModel record);

    answerModel selectByPrimaryKey(String answerid);

    int updateByPrimaryKeySelective(answerModel record);

    int updateByPrimaryKey(answerModel record);
}