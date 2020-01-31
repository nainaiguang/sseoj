package com.ustc.sse.sseoj.dao.singleModel;

import com.ustc.sse.sseoj.model.user.admin_infoModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 15:45
 */

@Mapper
public interface admin_infoModelMapper {
    int deleteByPrimaryKey(String no);

    int insert(admin_infoModel record);

    int insertSelective(admin_infoModel record);

    admin_infoModel selectByPrimaryKey(String no);

    int updateByPrimaryKeySelective(admin_infoModel record);

    int updateByPrimaryKey(admin_infoModel record);
}