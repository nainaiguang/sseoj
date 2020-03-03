package com.ustc.sse.sseoj.judgeSystem.service;

import com.ustc.sse.sseoj.Data.Code;
import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.dao.singleModel.warehouse.question_judge_problemModelMapper;
import com.ustc.sse.sseoj.judgeSystem.constant;
import com.ustc.sse.sseoj.judgeSystem.fileManager.fileCreater;
import com.ustc.sse.sseoj.judgeSystem.fileManager.ftpManager;
import com.ustc.sse.sseoj.judgeSystem.service.superService.answerService;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import com.ustc.sse.sseoj.model.warehouse.question_judge_problemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/3 14:17
 */
@Service
public class answerServiceImpl implements answerService {
    @Autowired
    question_judge_problemModelMapper qjpmm;
    @Override
    public Result addOrUpdateAnswer(questionModel qm, answerModel am) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        if(am.getAnswerid()==null)
        {
            return new Result.Fail(Code.MISS_ANSWERID);
        }
        try{
            question_judge_problemModel qjm=qjpmm.selectByPrimaryKey(qm.getQuestionid());
            if(qjm.getJudgeProblemId()==null)
            {
                return new Result.Fail(Code.ERROR);
            }
            fileCreater filec=new fileCreater();
            if(am.getInput()!=null)
            {
                filec.mkFile(am.getAnswerid()+".in");
                filec.modifyFileContent(am.getAnswerid()+".in",am.getInput());
            }
            if(am.getOutput()!=null)
            {
                filec.mkFile(am.getAnswerid()+".out");
                filec.modifyFileContent(am.getAnswerid()+".out",am.getOutput());
            }

            ftpManager ftpm=new ftpManager();
            if(am.getInput()!=null)
            {
                ftpm.upload(constant.cacheFileLocation+am.getAnswerid()+".in",qjm.getJudgeProblemId().toString()+"/");
            }
            if(am.getOutput()!=null)
            {
                ftpm.upload(constant.cacheFileLocation+am.getAnswerid()+".out",qjm.getJudgeProblemId().toString()+"/");
            }


            ftpm.closeFtpConnect();
            if(am.getInput()!=null)
            {
                filec.deleteFile(am.getAnswerid()+".in");
            }
            if(am.getOutput()!=null)
            {
                filec.deleteFile(am.getAnswerid()+".out");
            }


            return new Result.Success(true);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }


    }


    @Override
    public Result deleteAnswer(questionModel qm,answerModel am) {
        if(qm.getQuestionid()==null)
        {
            return new Result.Fail(Code.MISS_QUESTIONID);
        }
        if(am.getAnswerid()==null)
        {
            return new Result.Fail(Code.MISS_ANSWERID);
        }
        try{
            question_judge_problemModel qjm=qjpmm.selectByPrimaryKey(qm.getQuestionid());
            if(qjm.getJudgeProblemId()==null)
            {
                return new Result.Fail(Code.ERROR);
            }


            ftpManager ftpm=new ftpManager();
            ftpm.deleteFile(qjm.getJudgeProblemId()+"/"+am.getAnswerid()+".in");
            ftpm.deleteFile(qjm.getJudgeProblemId()+"/"+am.getAnswerid()+".out");
            ftpm.closeFtpConnect();

            return new Result.Success(true);
        }
        catch (Exception e)
        {
            return new Result.Error(e);
        }
    }


}
