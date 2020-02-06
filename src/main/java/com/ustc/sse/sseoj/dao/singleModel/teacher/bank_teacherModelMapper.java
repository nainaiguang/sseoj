package com.ustc.sse.sseoj.dao.singleModel.teacher;

import com.ustc.sse.sseoj.model.teacher.bank_teacherModelKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface bank_teacherModelMapper {
    int deleteByPrimaryKey(bank_teacherModelKey key);

    int insert(bank_teacherModelKey record);

    int insertSelective(bank_teacherModelKey record);
}