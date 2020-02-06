package com.ustc.sse.sseoj.dao.singleModel.warehouse;

import com.ustc.sse.sseoj.model.warehouse.question_answerModelKey;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
public interface question_answerModelMapper {
    int deleteByPrimaryKey(question_answerModelKey key);

    int insert(question_answerModelKey record);

    int insertSelective(question_answerModelKey record);
}