package com.ustc.sse.sseoj.dao.singleModel;

import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface select_courseModelMapper {
    int deleteByPrimaryKey(select_courseModelKey key);

    int insert(select_courseModelKey record);

    int insertSelective(select_courseModelKey record);
}