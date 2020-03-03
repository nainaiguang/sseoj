package com.ustc.sse.sseoj.judgeSystem.dao;

import com.ustc.sse.sseoj.judgeSystem.Model.problemModel;
import com.ustc.sse.sseoj.judgeSystem.Statement.Statements;
import com.ustc.sse.sseoj.judgeSystem.dao.sql.createSql;

import java.sql.SQLException;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 18:18
 */

public class problemDao {
    private Statements statements;

    //在jul中增加问题
    public int insert(problemModel pm)
    {
        statements=new Statements();
        String sql=createSql.insertJudgeProblem(pm);
        System.out.println(sql);
        try {
            int a= statements.getStatement().executeUpdate(sql);
            statements.close();
            return a;
        } catch (SQLException e) {
            e.printStackTrace();

        }

        try {
            statements.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //在jul中删除问题
    public boolean delete(problemModel pm)
    {
        statements=new Statements();
        String sql=createSql.deleteJudgeProblem(pm.getProblem_id());
        System.out.println(sql);
        try {
            boolean a=statements.getStatement().execute(sql);
            statements.close();
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statements.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int update(problemModel pm)
    {
        statements=new Statements();
        String sql=createSql.updateProblem(pm);
        System.out.println(sql);

        try{
            int a=statements.getStatement().executeUpdate(sql);
            statements.close();
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statements.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
