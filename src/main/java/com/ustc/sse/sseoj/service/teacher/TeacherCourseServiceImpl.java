package com.ustc.sse.sseoj.service.teacher;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.Data.IDType;
import com.ustc.sse.sseoj.dao.teacher.course.courseDAO;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.Curricula_variableModel;
import com.ustc.sse.sseoj.service.teacher.superService.teacherCourseService;
import com.ustc.sse.sseoj.util.CreatId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/21 16:51
 */

@Service
public class TeacherCourseServiceImpl implements teacherCourseService {

    @Autowired
    private courseDAO courseado;

    //老师增加课程
    @Override
    public Result teacher_add_course(CourseModel courseModel, Curricula_variableModel curricula_variableModel) {
        if(courseModel.getName()==null)//忘记填名字
        {
            return new Result.Fail(Code.MISS_COIRSE_NAME);
        }
        if(curricula_variableModel.getTno()==null)//忘记填教工号
        {
            return new Result.Fail(Code.MISS_TNO);
        }


        if(courseado.select_course_from_name(courseModel)!=null)//课程已存在
        {
            return new Result.Fail(Code.COURSE_ALREADY_EXISTS);
        }

        String courseID= CreatId.getSole_id(IDType.courseID);//生成唯一课程id

        courseModel.setCourseID(courseID);//设置课程id
        curricula_variableModel.setCourseID(courseID);//设置课程id

        boolean cmResult=courseado.insert_course(courseModel);



        if(cmResult==true)//插入成功
        {
            boolean cvresult= courseado.insert_curricula_variable(curricula_variableModel);
            if(cvresult==false)
            {
                return new Result.Fail(Code.WRONG_TNO);//错误教工号
            }
            return new Result.Success(courseModel);
        }
        else
        {
            return new Result.Fail(Code.UNKNOWN_WRONG);
        }
    }

    @Override
    public Result teacher_show_somebody_all_course(Curricula_variableModel curricula_variableModel) {

        if(curricula_variableModel.getTno()==null)
        {
            return new Result.Fail(Code.MISS_TNO);
        }
        ArrayList<CourseModel> arrayList=courseado.select_course_from_tno(curricula_variableModel);
        return new Result.Success(arrayList);

    }

    @Override
    public Result teacher_change_courseName(CourseModel courseModel) {
        if(courseModel.getCourseID()==null)
        {
            return new Result.Fail(Code.MISS_COURSEID);
        }
        if(courseModel.getName()==null)
        {
            return new Result.Fail(Code.MISS_NEW_NAME);
        }
        // fixme 缺少判断该课程是否存在，节省一次sql查询，因为一般认为从前端传过来的改名请求，都是已经存在的courseID

        boolean success=courseado.update_courseName_from_courseID(courseModel);
        if(success)
        {
            return new Result.Success(true);//TODO 改成mes
        }
        else
        {
            return new Result.Fail(Code.UNKNOWN_WRONG);
        }

    }

    @Override
    public Result teacher_delete_course(CourseModel courseModel) {
        if(courseModel.getCourseID()==null)
        {
            return new Result.Fail(Code.MISS_COURSEID);
        }

        boolean success=courseado.delete_course_from_courseID(courseModel);
        if(success)
        {
            return new Result.Success(true);//TODO 改成mes
        }
        else
        {
            return new Result.Fail(Code.UNKNOWN_WRONG);
        }

    }

    @Override
    public Result teacher_search_course_fully(CourseModel courseModel, Curricula_variableModel curricula_variableModel) {
        if(courseModel.getName()==null)
        {
            return new Result.Fail(Code.MISS_COIRSE_NAME);
        }
        if(curricula_variableModel.getTno()==null)
        {
            return new Result.Fail(Code.MISS_TNO);
        }
        ArrayList<CourseModel> arrayList=courseado.select_course_from_tno_fuzzyCourseName(courseModel,curricula_variableModel);
        return new Result.Success(arrayList);
    }

    @Override
    public Result teacher_search_scourse_by_courseID(CourseModel courseModel) {

        return new Result.Success(courseado.select_course_from_courseID(courseModel));
    }


}
