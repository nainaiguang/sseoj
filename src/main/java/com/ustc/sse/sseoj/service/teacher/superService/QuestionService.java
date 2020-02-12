package com.ustc.sse.sseoj.service.teacher.superService;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.teacher.homework_link_bankModelKey;
import com.ustc.sse.sseoj.model.user.TeacherModel;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/2 18:54
 * 对单次作业进行操作
 */

public interface QuestionService {


    //添加问题（包括作业与问题的关系），但添加教师关系
    public Result add_question(TeacherModel tm,homeworkModel hm, questionModel qm);

    //添加问题与课程关系
    public Result add_relationship_homework_question(homework_link_bankModelKey hlbm);

    //获取该教师的，某作业的所有题目（包括模糊）
    public  Result get_all_question_from_homework(TeacherModel tm,homeworkModel hm,questionModel qm);

    //搜索题目,该老师的（包括模糊搜索，根据题目名）
    public Result search_question(TeacherModel tm, questionModel qm);

    //更新问题
    public Result update_question(questionModel qm);

    //删除问题与作业关系
    public Result delete_relationship_homework_question(homework_link_bankModelKey hlbm);

    //删除问题
    public Result delete_question(questionModel qm);

    //搜索该题目几个作业在使用
    public Result get_homework_use_question(questionModel qm,TeacherModel tm);

    //得到某个题目详细信息
    public Result get_question_detail(questionModel qm);


    //搜索该老师的，该作业外的其他题目,包括模糊
    public Result get_question_except_using(TeacherModel tm,homeworkModel hm);

    //得到某个答案详细信息
    public Result get_answer_detail(answerModel am);

    //得到某个题目的所有样例
    public  Result get_all_case(questionModel qm);

    //得到某个题目的所有答案
    public Result get_all_answer(questionModel qm);

    //添加答案 案例（连接题目与答案）
    public Result add_answer_or_case(questionModel qm,answerModel am);

    //修改答案
    public  Result update_answer_or_case(answerModel am);

    //删除答案
    public Result  delete_answer_or_case(answerModel am);

}
