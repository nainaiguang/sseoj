package com.ustc.sse.sseoj.judgeSystem.dao.sql;

import com.ustc.sse.sseoj.judgeSystem.Model.problemModel;
import com.ustc.sse.sseoj.judgeSystem.Model.solutionModel;
import com.ustc.sse.sseoj.judgeSystem.Model.source_codeModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String getAllAnswerFromQuestion(questionModel qm)
    {
        String sql="SELECT\n" +
                "\tanswer.*\n" +
                "FROM\n" +
                "\tquestion_answer\n" +
                "INNER JOIN answer ON question_answer.answerID = answer.answerID\n" +
                "WHERE\n" +
                "\tquestionID = '" +qm.getQuestionid()+"'\n" +
                "AND answer.answer_type = 'answers'";
        return sql;
    }

    public static  String insertToSolution(solutionModel sm)
    {
        String sql="INSERT INTO solution (\n" +
                "\tsolution.solution_id,\n" +
                "\tsolution.problem_id,\n" +
                "\tsolution.user_id,\n" +
                "\tsolution.in_date,\n" +
                "\tsolution.`language`,\n" +
                "\tsolution.ip\n" +
                ")\n" +
                "VALUES\n" +
                "\t(\n" +
                sm.getSolution_id()+","+
                sm.getProblem_id()+","+
                "'"+sm.getUser_id()+"',"+
                "'"+sm.getIn_date()+"',"+
                sm.getLanguage()+","+
                "'"+sm.getIp()+"'"+
                "\t)";
        return sql;
    }
    public static String insertIntoSourceCode(source_codeModel scm)
    {
        String sql="INSERT INTO source_code (\n" +
                "\tsource_code.solution_id,\n" +
                "\tsource_code.source\n" +
                ")\n" +
                "VALUES\n" +
                "\t(\n" +
                scm.getSolution_id()+","+
                "'"+scm.getSource()+"'"+
                "\t)";
        return sql;
    }
    public static String selectjudgeResult(int solution_id)
    {
        String sql="SELECT\n" +
                "\tsolution.solution_id,\n" +
                "\tsolution.user_id,\n" +
                "\tsolution.time,\n" +
                "\tsolution.memory,\n" +
                "\tsolution.result,\n" +
                "\tcompileinfo.error as compileinfo_error,\n" +
                "\truntimeinfo.error as runtimeinfo_error\n" +
                "FROM\n" +
                "\tsolution\n" +
                "LEFT JOIN compileinfo ON solution.solution_id = compileinfo.solution_id\n" +
                "LEFT JOIN runtimeinfo ON solution.solution_id = runtimeinfo.solution_id\n" +
                "WHERE\n" +
                "\tsolution.solution_id ="+ solution_id;
        return sql;
    }



    public static void main(String[] args) {
        solutionModel solutionM=new solutionModel();
        solutionM.setLanguage(1);
        solutionM.setSolution_id(1022);
        solutionM.setProblem_id(1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        solutionM.setIn_date(df.format(date));
        System.out.println(insertToSolution(solutionM));

        source_codeModel scm=new source_codeModel();
        scm.setSolution_id(1022);
        scm.setSource("#include <iostream>\n" +
                "using namespace std;\n" +
                "int main() {\n" +
                "    int a,b;\n" +
                "    while (cin >> a >> b) {\n" +
                "        cout << a+b << endl;\n" +
                "    }\n" +
                "\treturn 0;\n" +
                "}\n");

        System.out.println(insertIntoSourceCode(scm));
    }
}
