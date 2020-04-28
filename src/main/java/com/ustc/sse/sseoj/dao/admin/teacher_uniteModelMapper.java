package com.ustc.sse.sseoj.dao.admin;


import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.user.uniteModel.teacher_uniteModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO 获取目前的所有老师(可以根据院系，年级来筛选,名字，教师号查询)
 * @Author 沙政鑫
 * @Data 2020/3/3
 */
@Mapper
public interface teacher_uniteModelMapper {
    //根据院系，年级，姓名模糊，学号查询，返回student_uniteModel
    ArrayList<teacher_uniteModel> selectTeacherByChoose(@Param("record") teacher_uniteModel record, @Param("pl") pageLimit pl);

    //返回数据数量count
    count selectCountByChoose(teacher_uniteModel record);

    boolean updateInfoFromTeacherID(teacher_uniteModel record);

    //批量导入教师
    int insertTeacherByExcel(List<teacher_uniteModel> teacherList);
    //批量导入教师信息
    int insertTeacherInfoByExcel(List<teacher_uniteModel> teacherList);
}
