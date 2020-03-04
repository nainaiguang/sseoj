package com.ustc.sse.sseoj.service.student;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.singleModel.student.select_courseModelMapper;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.service.student.superService.StudentCourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author 沙政鑫
 * @Data 2020/2/18  21:22
 */

@Service
public class StudentCourseServiceImpl implements StudentCourceService {
    @Autowired
    select_courseModelMapper scmm;



    @Override
    //管理员为学生选课
    public Result add_one_course_for_student(select_courseModelKey scmk) {
        if(scmk.getSno()==null){
            return new Result.Fail(Code.MISS_STUDENTID);
        }

        if(scmk.getCourseid()==null){
            return new Result.Fail(Code.MISS_COURSEID);
        }

        try {
            scmm.insert(scmk);
            return new Result.Success(true);
        }catch (Exception e){
            return new Result.Error(e);
        }
    }

    @Override
    public Result delete_one_cource_for_student(select_courseModelKey scmk) {
        if(scmk.getCourseid()==null){
            return new Result.Fail(Code.MISS_COURSEID);
        }
        try {
            int success = scmm.deleteByPrimaryKey(scmk);
            if (success>0) {
                return new Result.Success(true);
            } else {
                return new Result.Fail(Code.UNKNOWN_WRONG);
            }
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }






}
