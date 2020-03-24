package com.ustc.sse.sseoj.judgeSystem.dao;

import com.ustc.sse.sseoj.judgeSystem.Model.judgeResultModel;
import com.ustc.sse.sseoj.judgeSystem.Statement.Statements;
import com.ustc.sse.sseoj.judgeSystem.dao.sql.createSql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/22 21:30
 */
public class selectSolutionDao {
    private Statements statements;

    public selectSolutionDao() {
        this.statements=new Statements();
    }

    public judgeResultModel selectJudgeResult(int solution_id)
    {
        statements=new Statements();
        String sql= createSql.selectjudgeResult(solution_id);
        ResultSet rs;
        System.out.println(sql);
        try {
            rs=statements.getStatement().executeQuery(sql);
            if(rs.next())//查到了
            {
                judgeResultModel jm=new judgeResultModel();
                jm.setSolution_id(rs.getInt("solution_id"));
                jm.setUser_id(rs.getString("user_id"));
                jm.setTime(rs.getInt("time"));
                jm.setMemory(rs.getInt("memory"));
                jm.setResult(rs.getInt("result"));
                jm.setCompileinfo_error(rs.getString("compileinfo_error"));
                jm.setRuntimeinfo_error(rs.getString("runtimeinfo_error"));
                return jm;
            }
            else
            {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void disconnectJDBC()
    {
        try {
            this.statements.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
