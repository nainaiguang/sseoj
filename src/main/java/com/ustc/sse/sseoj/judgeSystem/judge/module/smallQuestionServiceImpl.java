package com.ustc.sse.sseoj.judgeSystem.judge.module;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.singleModel.warehouse.answerModelMapper;
import com.ustc.sse.sseoj.dao.teacher.homework.QuestionDao;
import com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces.smallQuestionService;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/15 13:40
 * 缩小版问题服务，为了缩小内存
 */
@Service
public class smallQuestionServiceImpl implements smallQuestionService {
    @Autowired
    answerModelMapper ammp;

    @Autowired
    QuestionDao qdao;

    //得到某个题目的所有答案
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

    //得到某个答案详细信息
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

}
