package com.ustc.sse.sseoj.service.warehouse;

import com.ustc.sse.sseoj.dao.singleModel.student.student_homeworkModelMapper;
import com.ustc.sse.sseoj.service.warehouse.superService.judgeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/15 13:26
 * 判题开线程，不要在服务的主线程做，防止出问题
 */
public class judgeServiceImpl implements judgeService {
    @Autowired
    student_homeworkModelMapper shmm;
}
