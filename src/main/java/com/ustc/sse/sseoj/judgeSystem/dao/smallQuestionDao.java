package com.ustc.sse.sseoj.judgeSystem.dao;

import com.ustc.sse.sseoj.judgeSystem.Statement.Statements;
import com.ustc.sse.sseoj.judgeSystem.dao.sql.createSql;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;

import java.util.ArrayList;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/15 20:21
 */
public class smallQuestionDao {
    private Statements statements;
    public ArrayList<answerModel> getAllAnswerFromQuestion(questionModel qm)
    {
        String sql= createSql.getAllAnswerFromQuestion(qm);
        return null;
    }
}
