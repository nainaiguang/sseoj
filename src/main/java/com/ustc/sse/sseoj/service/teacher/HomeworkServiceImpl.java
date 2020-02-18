package com.ustc.sse.sseoj.service.teacher;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.IDType;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.singleModel.teacher.course_homeworkModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.teacher.homeworkModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.teacher.teacher_homeworkModelMapper;
import com.ustc.sse.sseoj.dao.teacher.homework.HomeworkDao;
import com.ustc.sse.sseoj.model.functionClass.count;
import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import com.ustc.sse.sseoj.model.teacher.CourseModel;
import com.ustc.sse.sseoj.model.teacher.course_homeworkModelKey;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.teacher.teacher_homeworkModelKey;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import com.ustc.sse.sseoj.service.teacher.superService.HomeworkService;
import com.ustc.sse.sseoj.util.CreatId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/2 20:06
 */
@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    homeworkModelMapper hwmm;
    @Autowired
    teacher_homeworkModelMapper thmm;
    @Autowired
    course_homeworkModelMapper chmm;

    @Autowired
    HomeworkDao homeworkDao;

    @Override
    public Result get_one_homework_detail(homeworkModel hm) {
        if(hm.getHomeworkid()==null)
        {
            return new Result.Fail(Code.MISS_HOMEWORKID);
        }
        try
        {
            homeworkModel hmres= hwmm.selectByPrimaryKey(hm.getHomeworkid());
            return new Result.Success(hmres);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    //教师添加作业
    //其中 如果作业本身不存在，则创建作业，否则不创建
    // 且课程id不存在，则添加作业，连接教师与作业
    //如果课程id存在，则则添加作业，连接教师与作业
    @Override
    public Result teacher_add_homework(TeacherModel tm, homeworkModel hm, CourseModel cm) {
        if(hm.getHomeworkid()!=null&&cm.getCourseID()==null)
        {
            return new Result.Fail(Code.WRONG_PARAMETER);
        }

        if(hm.getHomeworkid()==null)//设置作业id,创建id,
        {
            if(tm.getTno()==null)//缺失教师号，无法创建新作业
            {
                return new Result.Fail(Code.MISS_TNO);
            }
            if(hm.getName()==null)
            {
                return new Result.Fail(Code.MISS_HOMEWORKNAME);
            }
            if(hm.getDescribes()==null)
            {
                hm.setDescribes("无");
            }


            hm.setHomeworkid(CreatId.getSole_id(IDType.homeworkID));
            try{
                hwmm.insertSelective(hm);//插入作业表
                teacher_homeworkModelKey thmk=new teacher_homeworkModelKey();
                thmk.setTno(tm.getTno());
                thmk.setHomeworkid(hm.getHomeworkid());
                thmm.insert(thmk);//插入教师作业关联表
            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }
        }


        if(cm.getCourseID()!=null&&cm.getCourseID()!="")//绑定课程
        {
            try{
                course_homeworkModelKey chmk=new course_homeworkModelKey();
                chmk.setCourseid(cm.getCourseID());
                chmk.setHomeworkid(hm.getHomeworkid());
                chmm.insert(chmk);
            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }
        }

    return new Result.Success(true);

    }

    //教师删除作业，直接删除，包括关系,包括批量 需要 homeworkid
    @Override
    public Result delete_homework(ArrayList<homeworkModel> arrayList) {
        if(arrayList.size()==0)
        {
            return new Result.Fail(Code.EMPTY_LIST);
        }

        ArrayList<Result> arrayres=new ArrayList<>();

        for(homeworkModel temphm:arrayList)
        {
            if(temphm.getHomeworkid()==null)
            {
                arrayres.add(new Result.Fail(Code.MISS_COURSEID));
                continue;
            }
            try
            {
                System.out.println("hello"+temphm.getHomeworkid());
                hwmm.deleteByPrimaryKey(temphm.getHomeworkid());
                arrayres.add(new Result.Success(true));
            }
            catch (Exception e)
            {
                arrayres.add(new Result.Error(e));
            }
        }

        return new Result.Success(arrayres);
    }

    //移除课程与作业的关系，包括批量
    @Override
    public Result delete_homework_link_with_course(ArrayList<course_homeworkModelKey> arrayList) {
        if(arrayList.size()==0)
        {
            return new Result.Fail(Code.EMPTY_LIST);
        }

        ArrayList<Result> arrayres=new ArrayList<>();
        for(course_homeworkModelKey tempchm:arrayList)
        {
            if(tempchm.getCourseid()==null)
            {
                arrayres.add(new Result.Fail(Code.MISS_COURSEID));
                continue;
            }
            if(tempchm.getHomeworkid()==null)
            {
                arrayres.add(new Result.Fail(Code.MISS_HOMEWORKID));
                continue;
            }
            try {
                chmm.deleteByPrimaryKey(tempchm);
                arrayres.add(new Result.Success(true));
            }
            catch (Exception e)
            {
                arrayres.add(new Result.Error(e));
            }
        }
        return new Result.Success(arrayres);
    }

    //更新作业信息，其中包括 名称，描述，开始时间，结束时间
    @Override
    public Result update_homework(homeworkModel hm) {
        if(hm.getHomeworkid()==null)
        {
           return new Result.Fail(Code.MISS_HOMEWORKID);
        }
        try {
            hwmm.updateByPrimaryKeySelective(hm);
            return new Result.Success(true);
        }
        catch (Exception e)
        {
           return new Result.Error(e);
        }

    }

    //显示某老师的作业课程，包括模糊查询，其中包括在某门课之下的查询和该老师所有课之下的查询
    //某门课下的所有作业，包括名字模糊查询 空即查询所有
    //某老师的所有作业，包括名字模糊查询 空即查询所有
    //优先查询某门课下
    @Override
    public Result search_homework(TeacherModel tm,CourseModel cm, homeworkModel hm, pageLimit pl) {
        if(cm.getCourseID()==null&&tm.getTno()==null)
        {
            return new Result.Fail(Code.MISS_PARAMETE);
        }
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        if(cm.getCourseID()!=null)
        {
            if(hm.getName()==null)
            {
                hm.setName("");
            }
            try{
                ArrayList<homeworkModel> arrayres=homeworkDao.search_homework_for_name_fully_in_course(tm,cm,hm,pl);
                return new Result.Success(arrayres);
            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }
        }

        if(tm.getTno()!=null)
        {
            if(hm.getName()==null)
            {
                hm.setName("");
            }
            try{
                ArrayList<homeworkModel> arrayres=homeworkDao.search_homework_for_name_fully_in_teacher(tm,hm,pl);
                return new Result.Success(arrayres);
            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }
        }
        return new Result.Fail(Code.ERROR);
    }

    //显示某老师的作业课程，包括模糊查询，其中包括在某门课之下的查询和该老师所有课之下的查询
    //某门课下的所有作业，包括名字模糊查询 空即查询所有
    //某老师的所有作业，包括名字模糊查询 空即查询所有
    //优先查询某门课下
    //的数量
    @Override
    public Result search_homework_count(TeacherModel tm,CourseModel cm, homeworkModel hm) {
        if(cm.getCourseID()==null&&tm.getTno()==null)
        {
            return new Result.Fail(Code.MISS_PARAMETE);
        }
        if(cm.getCourseID()!=null)
        {
            if(hm.getName()==null)
            {
                hm.setName("");
            }
            try{
                count arrayres=homeworkDao.search_count_homework_for_name_fully_in_course(tm,cm,hm);
                return new Result.Success(arrayres);
            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }
        }

        if(tm.getTno()!=null)
        {
            if(hm.getName()==null)
            {
                hm.setName("");
            }
            try{
                count arrayres=homeworkDao.search_count_homework_for_name_fully_in_teacher(tm,hm);
                return new Result.Success(arrayres);
            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }
        }
        return new Result.Fail(Code.ERROR);
    }

    //显示这个作业哪几门在用
    @Override
    public Result people_use_homework(homeworkModel hm) {
        if(hm.getHomeworkid()==null)
        {
            return new Result.Fail(Code.MISS_HOMEWORKID);
        }

        try{
            ArrayList<CourseModel> arrayres=homeworkDao.search_course_using_homework(hm);
            return new Result.Success(arrayres);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }


    }

    //显示目前属于该教师，但没有在该课程下的所有作业
    @Override
    public Result search_homework_without_using(TeacherModel tm, CourseModel cm,homeworkModel hm,pageLimit pl) {
        if(tm.getTno()==null)
        {
            return new Result.Fail(Code.MISS_TNO);
        }
        if(cm.getCourseID()==null)
        {
            return new Result.Fail(Code.MISS_COURSEID);
        }
        if(hm.getName()==null)
        {
            hm.setName("");
        }
        if(pl.getLimit()==0)
        {
            return new Result.Fail(Code.MISS_PAGE_LIMIT);
        }
        try{
            ArrayList<homeworkModel> arrayRes=homeworkDao.search_homework_without_using(tm,cm,hm,pl);
            return new Result.Success(arrayRes);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    //显示目前属于该教师，但没有在该课程下的所有作业数量
    @Override
    public Result search_count_homework_without_using(TeacherModel tm, CourseModel cm,homeworkModel hm) {
        if(tm.getTno()==null)
        {
            return new Result.Fail(Code.MISS_TNO);
        }
        if(cm.getCourseID()==null)
        {
            return new Result.Fail(Code.MISS_COURSEID);
        }
        if(hm.getName()==null)
        {
            hm.setName("");
        }
        try{
            count arrayRes=homeworkDao.search_count_homework_without_using(tm,cm,hm);
            return new Result.Success(arrayRes);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }
}
