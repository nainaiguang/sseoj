package com.ustc.sse.sseoj.dao.admin;

import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.user.uniteModel.student_uniteModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * @Description TODO 获取目前的所有学生(可以根据院系，年级来筛选,名字)
 * @Author 沙政鑫
 * @Data 2020/2/25
 */

@Mapper
public interface student_uniteModelMapper {

     //根据院系，年级，姓名模糊，学号查询，返回student_uniteModel
     ArrayList<student_uniteModel> selectStudentByChoose(@Param("record") student_uniteModel record, @Param("pl") pageLimit pl);

     //返回数据数量count
     count selectCountByChoose(student_uniteModel record);
}
