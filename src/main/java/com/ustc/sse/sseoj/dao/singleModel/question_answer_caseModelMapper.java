package com.ustc.sse.sseoj.dao.singleModel;

import com.ustc.sse.sseoj.model.warehouse.question_answer_caseModelKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface question_answer_caseModelMapper {
    int deleteByPrimaryKey(question_answer_caseModelKey key);

    int insert(question_answer_caseModelKey record);

    int insertSelective(question_answer_caseModelKey record);
}