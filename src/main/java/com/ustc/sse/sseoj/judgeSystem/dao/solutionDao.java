package com.ustc.sse.sseoj.judgeSystem.dao;

import com.ustc.sse.sseoj.judgeSystem.Model.judgeResultModel;
import com.ustc.sse.sseoj.judgeSystem.Model.solutionModel;
import com.ustc.sse.sseoj.judgeSystem.Model.source_codeModel;
import com.ustc.sse.sseoj.judgeSystem.Statement.Statements;
import com.ustc.sse.sseoj.judgeSystem.dao.sql.createSql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/22 16:43
 */
public class solutionDao {
    private Statements statements;
    public int insertSolution(solutionModel sm)
    {

        statements=new Statements();
        String sql= createSql.insertToSolution(sm);
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

    public int insertSourceCode(source_codeModel scm)
    {
        statements=new Statements();
        String sql= createSql.insertIntoSourceCode(scm);
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


}
