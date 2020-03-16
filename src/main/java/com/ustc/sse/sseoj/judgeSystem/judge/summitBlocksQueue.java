package com.ustc.sse.sseoj.judgeSystem.judge;

import com.ustc.sse.sseoj.Data.Result;
import com.ustc.sse.sseoj.judgeSystem.constant;
import com.ustc.sse.sseoj.judgeSystem.judge.interfaces.getJudgeResultFeekback;
import com.ustc.sse.sseoj.judgeSystem.judge.module.diverter;
import com.ustc.sse.sseoj.judgeSystem.judge.module.judger;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;
import com.ustc.sse.sseoj.util.ApplicationContextProvider;
import com.ustc.sse.sseoj.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/9 17:52
 * 单例模式,保证全局唯一 Holder模式 使用jvm一开始就把队列加载好
 * 将懒加载和线程安全完美结合的一种方式（无锁）
 */

public class summitBlocksQueue extends Thread{
    /**
    * 静态初始化器，由JVM来保证线程安全
     */

    private static summitBlocksQueue summitBlocksQueues;

    private LinkedBlockingQueue<student_homeworkModelWithBLOBs> commit;//线程阻塞队列
    private Map<student_homeworkModelWithBLOBs, getJudgeResultFeekback> resMap;//限制键值对队列，用来回调方法,获取结果，使用时需要继承接口

    private ExecutorService executorService;//线程池，控制线程的数量，防止系统崩溃,即控制同时判题数，防止同时判题过多

    private summitBlocksQueue()
    {
        this.commit=new LinkedBlockingQueue<>();
        this.resMap=new HashMap<>();
        executorService= Executors.newFixedThreadPool(constant.threadnumber);
    }

    public static synchronized summitBlocksQueue getInstance()
    {

        if(summitBlocksQueues==null)
        {
            summitBlocksQueues=new summitBlocksQueue();
            summitBlocksQueues.start();
        }
        return summitBlocksQueues;
    }

    @Override
    public void run() {
        super.run();
        while (true)
        {
            System.out.println("循环一次");
            try {
                student_homeworkModelWithBLOBs shmwb=commit.take();//这里会阻塞


                Thread judgethread= new Thread(){
                    @Override
                    public void run() {
                        judger jd=new judger();
                        Result res=jd.judging(new diverter(),shmwb);//开始判题

                        getJudgeResultFeekback resfeekback=resMap.get(shmwb);
                        resfeekback.getJudgeResult(res);//设置回送结果
                        resMap.remove(shmwb);
                    }
                };
                //judgethread.start();
                executorService.submit(judgethread);//提交线程

                // todo 处理阻塞队列中的值
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addSummit(student_homeworkModelWithBLOBs shmwb,getJudgeResultFeekback feekback)
    {
        try {
            this.commit.put(shmwb);
            this.resMap.put(shmwb,feekback);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;//添加失败，给前端返回信息
        }
    }

}
