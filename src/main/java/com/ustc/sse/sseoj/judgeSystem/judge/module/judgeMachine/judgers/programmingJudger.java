package com.ustc.sse.sseoj.judgeSystem.judge.module.judgeMachine.judgers;

import com.ustc.sse.sseoj.Data.JudgeCode;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.judgeSystem.Model.judgeResultModel;
import com.ustc.sse.sseoj.judgeSystem.Model.solutionModel;
import com.ustc.sse.sseoj.judgeSystem.Model.source_codeModel;
import com.ustc.sse.sseoj.judgeSystem.dao.solutionDao;
import com.ustc.sse.sseoj.judgeSystem.judge.interfaces.judgePollerResultInterface;
import com.ustc.sse.sseoj.judgeSystem.judge.module.interfaces.judgerInterface;
import com.ustc.sse.sseoj.judgeSystem.judge.module.pollerBlocksQueue;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/14 16:40
 * 编程判题器
 */
@Component
public class programmingJudger implements judgerInterface {
    /**
     * @param shmb
     * 特殊需要 problem_id,old_solutionid,solutionid
     * @return
     */
    @Override
    public Result judge(student_homeworkModelWithBLOBs shmb) {
//        String sql="INSERT INTO source_code (\n" +
//                "\tsource_code.solution_id,\n" +
//                "\tsource_code.source\n" +
//                ")\n" +
//                "VALUES\n" +
//                "\t(\n" +
//                scm.getSolution_id()+","+
//                "'"+scm.getSource()+"'"+
//                "\t)";
        Result[] resback = {null};
        System.out.println("编程题判题中");
        solutionDao sd=new solutionDao();
        //插入到代码编写表
        source_codeModel scm=new source_codeModel();
        scm.setSource(shmb.getAnswers());
        scm.setSolution_id(shmb.getSolutionId());
        int res1=sd.insertSourceCode(scm);
        if(res1==0)
        {
            return new Result.Success(JudgeCode.SUMMIT_FAIL);
        }

        //插入到解决表
        solutionModel sm=new solutionModel();
        sm.setSolution_id(shmb.getSolutionId());
        sm.setProblem_id(shmb.getProblemId());
        sm.setIp(shmb.getIp());
        sm.setLanguage(shmb.getLangauge());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        sm.setIn_date(df.format(date));
        sm.setUser_id(shmb.getSno());
        int res2=sd.insertSolution(sm);

        if(res2==0)
        {
            return new Result.Success(JudgeCode.SUMMIT_FAIL);
        }


        pollerBlocksQueue.getInstance().addpooler(shmb.getOldSolutionId(), shmb.getSolutionId(), new judgePollerResultInterface() {
            @Override
            public void judgePollerResultFeekback(judgeResultModel jrm) {//轮询器返回的结果
                resback[0] =new Result.Success(jrm);
            }
        });
        //插入到代码表
        //插入到解决表
        //插入到轮询器
        //得到结果后返回
        while(true)
        {
            if(resback[0] !=null)
                return resback[0];
            else
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
