package com.ustc.sse.sseoj.controller.redirects.teacher;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.model.teacher.homeworkModel;
import com.ustc.sse.sseoj.model.warehouse.answerModel;
import com.ustc.sse.sseoj.model.warehouse.questionModel;
import com.ustc.sse.sseoj.service.teacher.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**

 /**
 * @author Qianbw
 * @create 2020-02-15 14:38
 * @desc 教师管理题库页面跳转的控制层
 **/
@Controller
@RequestMapping("/teacherQuestion")
public class Teacher2QuestionController {

    @Autowired
    QuestionServiceImpl qsimpl;

    /**
     * 功能描述: 进入作业下的题库管理页面
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/15 14:42
     */
    @RequestMapping("/toQuestion")
    public String toQuestion(Model model, homeworkModel hm) {
        model.addAttribute("homeworkid", hm.getHomeworkid());
        return "teacher/question/teaQuestion";
    }

    /**
     * 功能描述: 进入题库管理页面
     * @Param: [model]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/18 15:40
     */
    @RequestMapping("/toQuestionManage")
    public String toQuestionManage(Model model) {
        return "teacher/question/questionManage";
    }

    /**
     * 功能描述: 进入题目新增页面
     * @Param: [model, homeworkModel]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/15 19:00
     */
    @RequestMapping("/toAddQuestion")
    public String toAddQuestion(Model model, homeworkModel hm){
        model.addAttribute("homeworkid", hm.getHomeworkid());
        return "teacher/question/addQuestion";
    }

    /**
     * 功能描述: 进入题目编辑页面
     * @Param: [model, questionModel]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/15 20:26
     */
    @RequestMapping("/toEditQuestion")
    public String toEditQuestion(Model model, questionModel qm){
        Result result=qsimpl.get_question_detail(qm);
        if(result instanceof Result.Success)
        {
            qm = (questionModel) ((Result.Success) result).getData();
        }
        model.addAttribute("questionModel", qm);
        return "teacher/question/editQuestion";
    }

    /**
     * 功能描述: 进入题目查看页面
     * @Param: [model, questionModel]
     * @Return: java.lang.String
     * @Author: Wn
     * @Date: 2020/2/15 20:26
     */
    @RequestMapping("/toShowQuestion")
    public String toShowQuestion(Model model, questionModel qm){
        Result result=qsimpl.get_question_detail(qm);
        if(result instanceof Result.Success)
        {
            qm = (questionModel) ((Result.Success) result).getData();
        }
        model.addAttribute("questionModel", qm);
//        question_answerModelKey qam=new question_answerModelKey();
//       answerModel am=new answerModel();
//        Result result_a=qsimpl1.get_all_case(qm);
//
//        if(result_a instanceof Result.Success)
//        {
//            am = (answerModel) ((Result.Success) result).getData();
//
//        }
//        model.addAttribute("answerModel", am);

        return "teacher/question/showQuestion";
    }
    /**
     * 功能描述: 进入题目添加页面
     * @Param: [model, hm]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/16 21:29
     */
    @RequestMapping("/toSelectHomeworkQuestion")
    public String toSelectHomeworkQuestion(Model model, homeworkModel hm){
        model.addAttribute("homeworkid", hm.getHomeworkid());
        return "teacher/question/selectQuestion";
    }

    /**
     * 功能描述: 进入Case/Answer页面
     * @Param: [model, qm]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/16 21:51
     */
    @RequestMapping("/toCaseAnswer")
    public String toCaseAnswer(Model model, questionModel qm){
        model.addAttribute("questionModel", qm);
        return "teacher/question/caseAnswer";
    }

    /**
     * 功能描述: 进入新增Case页面
     * @Param: [model, qm]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/18 14:07
     */
    @RequestMapping("/toAddCase")
    public String toAddCase(Model model, questionModel qm){
        model.addAttribute("questionid", qm.getQuestionid());
        return "teacher/question/addCase";
    }

    /**
     * 功能描述: 进入新增Answer页面
     * @Param: [model, qm]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/25 20:10
     */
    @RequestMapping("/toAddAnswer")
    public String toAddAnswer(Model model, questionModel qm){
        model.addAttribute("questionModel", qm);
        return "teacher/question/addAnswer";
    }

    /**
     * 功能描述: 进入编辑Case页面
     * @Param: [model, am]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/18 14:07
     */
    @RequestMapping("/toEditCase")
    public String toEditCase(Model model, answerModel am, questionModel qm){
        Result result=qsimpl.get_answer_detail(am);
        if(result instanceof Result.Success)
        {
            am = (answerModel) ((Result.Success) result).getData();
        }
        model.addAttribute("answerModel", am);
        model.addAttribute("questionid", qm.getQuestionid());
        return "teacher/question/editCase";
    }

    /**
     * 功能描述: 进入编辑Answer页面
     * @Param: [model, am]
     * @Return: java.lang.String
     * @Author: Qianbw
     * @Date: 2020/2/25 20:22
     */
    @RequestMapping("/toEditAnswer")
    public String toEditAnswer(Model model, answerModel am, questionModel qm){
        Result result=qsimpl.get_answer_detail(am);
        if(result instanceof Result.Success)
        {
            am = (answerModel) ((Result.Success) result).getData();
        }
        model.addAttribute("answerModel", am);
        model.addAttribute("questionModel", qm);
        return "teacher/question/editAnswer";
    }
}
