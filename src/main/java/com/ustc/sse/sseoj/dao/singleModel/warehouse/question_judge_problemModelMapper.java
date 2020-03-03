package com.ustc.sse.sseoj.dao.singleModel.warehouse;

import com.ustc.sse.sseoj.model.warehouse.question_judge_problemModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 14:52
 */
@Mapper
public interface question_judge_problemModelMapper {
    int insert(question_judge_problemModel record);

    int insertSelective(question_judge_problemModel record);

    question_judge_problemModel selectByPrimaryKey(String questionid);

}