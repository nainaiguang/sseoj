package com.ustc.sse.sseoj.dao.singleModel;

import com.ustc.sse.sseoj.model.teacher.homework_link_bankModelKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface homework_link_bankModelMapper {
    int deleteByPrimaryKey(homework_link_bankModelKey key);

    int insert(homework_link_bankModelKey record);

    int insertSelective(homework_link_bankModelKey record);
}