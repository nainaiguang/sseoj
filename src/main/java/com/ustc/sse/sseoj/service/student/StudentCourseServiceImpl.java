package com.ustc.sse.sseoj.service.student;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.singleModel.student.select_courseModelMapper;
import com.ustc.sse.sseoj.dao.student.StudentDao;
import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.student.display_questionNum;
import com.ustc.sse.sseoj.model.student.select_courseModelKey;
import com.ustc.sse.sseoj.model.student.select_courselnfoModel;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.teacher.homework_link_bankModel;
import com.ustc.sse.sseoj.model.user.studentModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import com.ustc.sse.sseoj.service.student.superService.StudentCourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Description TODO
 * @Author 沙政鑫
 * @Data 2020/2/18  21:22
 */

@Service
public class StudentCourseServiceImpl implements StudentCourceService {
    @Autowired(required = false)
    StudentDao studentDao;

    //获取该学生的所有选课信息及相应教师
    @Override
    public Result select_courseInfo_from_student(studentModel sm, pageLimit pl) {
        if(sm.getNo()==null){
            return new Result.Fail(Code.MISS_STUDENTID);
        }
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        try {
            ArrayList<select_courselnfoModel> arrayList = studentDao.get_coursesInfo_from_student(sm,pl);
            return new Result.Success(arrayList);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result select_courseInfo_from_student(studentModel sm) {
        if(sm.getNo()==null){
            return new Result.Fail(Code.MISS_STUDENTID);
        }

        try {
            count count1  = studentDao.get_coursesInfo_nums_from_student(sm);
            return new Result.Success(count1);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    //显示该课程的所有作业
    @Override
    public Result select_homeworkInfo_from_courseID(CourseModel cm, pageLimit pl) {
        if(cm.getCourseID()==null){
            return new Result.Fail(Code.MISS_COURSEID);
        }
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        try {
            ArrayList<homeworkModel> arrayList = studentDao.get_homeworkInfo_from_courseID(cm,pl);
            return new Result.Success(arrayList);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result select_homeworkInfo_from_courseID(CourseModel cm) {
        if(cm.getCourseID()==null){
            return new Result.Fail(Code.MISS_COURSEID);
        }
        try {
            count count1  = studentDao.get_homeworkInfo_nums_from_courseID(cm);
            return new Result.Success(count1);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    //显示本次作业的所有题目，按题号排序.
    @Override
    public Result select_question_from_homeworkID(homeworkModel hm, pageLimit pl) {
        if(hm.getHomeworkid()==null){
            return new Result.Fail(Code.MISS_HOMEWORKID);
        }
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        try {
            ArrayList<display_questionNum> arrayList = studentDao.get_question_from_homeworkID(hm,pl);
            return new Result.Success(arrayList);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    @Override
    public Result select_question_from_homeworkID(homeworkModel hm) {
        if(hm.getHomeworkid()==null){
            return new Result.Fail(Code.MISS_HOMEWORKID);
        }
        try {
            count count1 = studentDao.get_question_nums_from_homeworkID(hm);
            return new Result.Success(count1);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }


    //根据题号获取本次作业的那一题
    @Override
    public Result select_question_from_num(homework_link_bankModel hlbm) {
        if(hlbm.getHomeworkid()==null){
            return new Result.Fail(Code.MISS_HOMEWORKID);
        }
        if(hlbm.getQuestionnumber()==null){
            return new Result.Fail(Code.Miss_QUESTIONNUMBER);
        }
        try {
            questionModel qm=studentDao.get_question_from_num(hlbm);
            return new Result.Success(qm);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }


//    @Override
//    //管理员为学生选课
//    public Result add_one_course_for_student(select_courseModelKey scmk) {
//        if(scmk.getSno()==null){
//            return new Result.Fail(Code.MISS_STUDENTID);
//        }
//
//        if(scmk.getCourseid()==null){
//            return new Result.Fail(Code.MISS_COURSEID);
//        }
//
//        try {
//            scmm.insert(scmk);
//            return new Result.Success(true);
//        }catch (Exception e){
//            return new Result.Error(e);
//        }
//    }
//
//    @Override
//    public Result delete_one_cource_for_student(select_courseModelKey scmk) {
//        if(scmk.getCourseid()==null){
//            return new Result.Fail(Code.MISS_COURSEID);
//        }
//        try {
//            int success = scmm.deleteByPrimaryKey(scmk);
//            if (success>0) {
//                return new Result.Success(true);
//            } else {
//                return new Result.Fail(Code.UNKNOWN_WRONG);
//            }
//        }
//        catch (Exception e)
//        {
//            return new Result.Error(e);
//        }
//    }






}
