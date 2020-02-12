package com.ustc.sse.sseoj.service.teacher;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.IDType;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.singleModel.teacher.bank_teacherModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.teacher.homework_link_bankModelMapper;
import com.ustc.sse.sseoj.dao.singleModel.warehouse.*;
import com.ustc.sse.sseoj.dao.teacher.homework.QuestionDao;
import com.ustc.sse.sseoj.model.teacher.bank_teacherModelKey;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.teacher.homework_link_bankModelKey;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import com.ustc.sse.sseoj.model.warehouse.question_answerModelKey;
import com.ustc.sse.sseoj.service.teacher.superService.QuestionService;
import com.ustc.sse.sseoj.util.CreatId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/9 18:45
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    homework_link_bankModelMapper hlbmp;
    @Autowired
    questionModelMapper qmmp;
    @Autowired
    answerModelMapper ammp;
    @Autowired
    question_answerModelMapper qammp;
    @Autowired
    bank_teacherModelMapper btmmp;
    @Autowired
    QuestionDao qdao;

    //添加问题（包括作业与问题的关系），但添加教师关系
    //TODO 添加图片还没写
    @Override
    public Result add_question(TeacherModel tm,homeworkModel hm, questionModel qm) {
        if(tm.getTno()==null)
        {
            return new Result.Fail(Code.MISS_TNO);
        }
        if(hm.getHomeworkid()==null)
        {
            return new Result.Fail(Code.MISS_HOMEWORKID);
        }
        if(qm.getQuestionid()==null)
        {
            if(qm.getQuestiontype()==null)
            {
                return new Result.Fail(Code.MISS_QUESTION_TYPE);
            }
            if(qm.getTitle()==null)
            {
                return new Result.Fail(Code.MISS_QUESTION_TITLE);
            }
            if(qm.getDescription()==null)
            {
                return new Result.Fail(Code.MISS_QUESTION_DESCRIPTION);
            }

            qm.setQuestionid(CreatId.getSole_id(IDType.questionID));
            try{
                qmmp.insert(qm);

                //增加教师与问题的关系
                bank_teacherModelKey btm=new bank_teacherModelKey();
                btm.setTno(tm.getTno());
                btm.setQuestionid(qm.getQuestionid());
                btmmp.insert(btm);

            }
            catch (Exception e)
            {
                return new Result.Error(e);
            }
        }

        homework_link_bankModelKey temphlbm=new homework_link_bankModelKey();
        temphlbm.setHomeworkid(hm.getHomeworkid());
        temphlbm.setQuestionid(qm.getQuestionid());
        Result res= add_relationship_homework_question(temphlbm);

        return res;

    }

    //添加问题与作业关系
    @Override
    public Result add_relationship_homework_question(homework_link_bankModelKey hlbm) {
        if(hlbm.getHomeworkid()==null)
        {
            return new Result.Fail(Code.MISS_HOMEWORKID);
        }
        if(hlbm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }

        try{
            hlbmp.insert(hlbm);
            return new Result.Success(true);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    //获取该教师的，某作业的所有题目
    @Override
    public Result get_all_question_from_homework(TeacherModel tm,homeworkModel hm,questionModel qm) {
        if(tm.getTno()==null)
        {
            return new Result.Fail(Code.MISS_TNO);
        }
        if(hm.getHomeworkid()==null)
        {
            return new Result.Fail(Code.MISS_HOMEWORKID);
        }
        if(qm.getTitle()==null)
        {
            qm.setTitle("");
        }
        try{
            ArrayList<questionModel> reslist= qdao.get_all_question_from_course_on_teacher(tm,hm,qm);
            return new Result.Success(reslist);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    //搜索题目,该老师的（包括模糊搜索，根据题目名）
    @Override
    public Result search_question(TeacherModel tm, questionModel qm) {
        if(tm.getTno()==null)
        {
            return new Result.Fail(Code.MISS_TNO);
        }
        if(qm.getTitle()==null)
        {
            qm.setTitle("");
        }

        try{
            ArrayList<questionModel> reslist= qdao.get_all_question_from_teacher(tm,qm);
            return new Result.Success(reslist);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }






    //更新问题  todo 测
    @Override
    public Result update_question(questionModel qm) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        try{
        int a= qmmp.updateByPrimaryKeySelective(qm);
        return new Result.Success(a);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    //删除问题与作业关系 todo 测
    @Override
    public Result delete_relationship_homework_question(homework_link_bankModelKey hlbm) {
        if(hlbm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        if(hlbm.getHomeworkid()==null)
        {
            return new Result.Fail(Code.MISS_HOMEWORKID);
        }

        try{
            int a=hlbmp.deleteByPrimaryKey(hlbm);
            return new Result.Success(a);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    //删除问题 todo 测
    @Override
    public Result delete_question(questionModel qm) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        try{
            int a=qmmp.deleteByPrimaryKey(qm.getQuestionid());
            return new Result.Success(a);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    //搜索该题目几个作业在使用
    @Override
    public Result get_homework_use_question(questionModel qm,TeacherModel tm) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        if(tm.getTno()==null)
        {
            return new Result.Fail(Code.MISS_TNO);
        }
        try{

            ArrayList<homeworkModel> arrayRes=qdao.get_all_homework_using_question(qm.getQuestionid(),tm.getTno());
            return new Result.Success(arrayRes);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    //得到某个题目详细信息
    @Override
    public Result get_question_detail(questionModel qm) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        try{
            questionModel tempqm= qmmp.selectByPrimaryKey(qm.getQuestionid());
            return new Result.Success(tempqm);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }



    //搜索该老师的，该作业外的其他题目 todo 测
    @Override
    public Result get_question_except_using(TeacherModel tm, homeworkModel hm) {
        if(tm.getTno()==null)
        {
            return new Result.Fail(Code.MISS_TNO);
        }
        if(hm.getHomeworkid()==null)
        {
            return new Result.Fail(Code.MISS_HOMEWORKID);
        }
        try{
            ArrayList<questionModel> reslist=qdao.get_question_except_using(tm,hm);
            return new Result.Success(reslist);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    //得到某个答案详细信息 todo 测
    @Override
    public Result get_answer_detail(answerModel am) {
        if(am.getAnswerid()==null)
        {
            return new Result.Fail(Code.MISS_ANSWERID);
        }
        try{
           answerModel res= ammp.selectByPrimaryKey(am.getAnswerid());
            return new Result.Success(res);

        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    //得到某个题目的所有样例 todo 测
    @Override
    public Result get_all_case(questionModel qm) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        try{
            ArrayList<answerModel> reslist=qdao.get_all_case(qm);
            return new Result.Success(reslist);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    //得到某个题目的所有答案 todo 测
    @Override
    public Result get_all_answer(questionModel qm) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        try{
            ArrayList<answerModel> reslist=qdao.get_all_answer(qm);
            return new Result.Success(reslist);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }

    }

    //添加答案 案例（连接题目与答案）
    @Override
    public Result add_answer_or_case(questionModel qm, answerModel am) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        am.setAnswerid(CreatId.getSole_id(IDType.answerID));
        try{
            ammp.insertSelective(am);
            question_answerModelKey tempqam=new question_answerModelKey();
            tempqam.setAnswerid(am.getAnswerid());
            tempqam.setQuestionid(qm.getQuestionid());
            qammp.insert(tempqam);
            return new Result.Success(true);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new Result.Error(e);
        }
    }

    //修改答案 todo 测
    @Override
    public Result update_answer_or_case(answerModel am) {
        if(am.getAnswerid()==null)
        {
            return new Result.Fail(Code.MISS_ANSWERID);
        }
        try{
            int res= ammp.updateByPrimaryKeySelective(am);
            return new Result.Success(res);

        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }

    //删除答案 todo 测
    @Override
    public Result delete_answer_or_case(answerModel am) {
        if(am.getAnswerid()==null)
        {
            return new Result.Fail(Code.MISS_ANSWERID);
        }
        try{
            int res= ammp.deleteByPrimaryKey(am.getAnswerid());
            return new Result.Success(res);

        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }
}
