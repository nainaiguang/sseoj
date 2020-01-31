package com.ustc.sse.sseoj.dao.singleModel;

import com.ustc.sse.sseoj.model.warehouse.answer_caseModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface answer_caseModelMapper {
    int deleteByPrimaryKey(String answercaseid);

    int insert(answer_caseModel record);

    int insertSelective(answer_caseModel record);

    answer_caseModel selectByPrimaryKey(String answercaseid);

    int updateByPrimaryKeySelective(answer_caseModel record);

    int updateByPrimaryKey(answer_caseModel record);
}