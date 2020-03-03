package com.ustc.sse.sseoj.judgeSystem.dao.sql;

import com.ustc.sse.sseoj.judgeSystem.Model.problemModel;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 16:55
 */
public class createSql {
    public static  String insertJudgeProblem(problemModel pm)
    {
        String sql="INSERT INTO problem (problem_id,title,time_limit,memory_limit) VALUES "+
                "( "+
                pm.getProblem_id()+","+
                "'"+pm.getTitle()+"',"+
                pm.getTime_limit()+","+
                pm.getMemory_limit()+")";
        return sql;
    }

    public static String deleteJudgeProblem(int problemID)
    {
        String sql="DELETE FROM problem where problem_id = '"+problemID+"'";
        return sql;
    }

    public static String updateProblem(problemModel pm)
    {
        //UPDATE table_name SET field1=new-value1, field2=new-value2
        String sql="UPDATE problem SET ";
        if(pm.getTitle()!=null)
        {
            String tt="title = '"+pm.getTitle()+"' ";
            if(pm.getMemory_limit()!=-1||pm.getTime_limit()!=-1)
            {
                tt=tt+", ";
            }
            sql=sql+tt;
        }
        if(pm.getTime_limit()!=-1)
        {
            String tt="time_limit = "+pm.getTime_limit()+" ";
            if(pm.getMemory_limit()!=-1)
            {
                tt=tt+", ";
            }
            sql=sql+tt;
        }
        if(pm.getMemory_limit()!=-1)
        {
            String tt="memory_limit = "+pm.getTime_limit()+" ";
            sql=sql+tt;
        }

        sql=sql+"where problem_id = '"+pm.getProblem_id()+"'";
        return sql;
    }
}
