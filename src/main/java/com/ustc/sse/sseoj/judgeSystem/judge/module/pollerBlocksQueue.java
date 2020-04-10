package com.ustc.sse.sseoj.judgeSystem.judge.module;


import com.ustc.sse.sseoj.judgeSystem.Model.judgeResultModel;
import com.ustc.sse.sseoj.judgeSystem.Statement.Statements;
import com.ustc.sse.sseoj.judgeSystem.constant;
import com.ustc.sse.sseoj.judgeSystem.dao.selectSolutionDao;
import com.ustc.sse.sseoj.judgeSystem.judge.interfaces.judgePollerResultInterface;
import com.ustc.sse.sseoj.model.student.student_homeworkModelWithBLOBs;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/9 14:38
 * 轮询器
 * 就是用来轮询目前应该被轮询查询的题目结果，不做任何其他事，不包括插入数据库
 */

public class pollerBlocksQueue extends Thread{

    private static pollerBlocksQueue pollers;
    private Set<Integer> polledNumber;//线程阻塞队列
    private Map<Integer, judgePollerResultInterface> resMap;//限制键值对队列，用来回调方法,获取结果，使用时需要继承接口
    private pollerBlocksQueue()
    {
        this.polledNumber=new HashSet<>();
        this.resMap=new HashMap<>();
    }


    public static synchronized pollerBlocksQueue getInstance()
    {
        if(pollers==null)
        {
            pollers=new pollerBlocksQueue();

            pollers.start();
        }
        return pollers;
    }

    @Override
    public void run() {
        super.run();
           while(true)
           {

               synchronized(this){ //锁对象

                   if(polledNumber.size()==0)
                   {
                       System.out.println("休眠一次");
                       try {
                               wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                   else //队列不为空
                   {
                       //需要判断获取的回调方法是否为空，如果为空就是被删掉了，那就不用回调啦
                       System.out.println(polledNumber.toString());
                       selectSolutionDao ssd=new selectSolutionDao();
                       try {

                           for(Integer i:polledNumber)
                           {
                            if(i!=null)
                            {
                                judgeResultModel res=ssd.selectJudgeResult(i);
                                if(res.getResult()!=0&&res.getResult()!=2)//成功获取结果
                                {
                                    System.out.println(res.getResult());
                                    judgePollerResultInterface resreback=resMap.get(i);
                                    if(resreback!=null)
                                    {
                                        resreback.judgePollerResultFeekback(res);
                                    }
                                    polledNumber.remove(i);
                                    resMap.remove(i);
                                }
                            }
                           }
                           ssd.disconnectJDBC();//关闭连接
                       }
                       catch ( Exception e)
                       {
                           e.printStackTrace();
                       }


                   }

                   try {
                       Thread.sleep(constant.pollerSleepTime);//一轮休眠
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
    }


    public boolean addpooler(Integer oldone, Integer newone,judgePollerResultInterface j)
    {
        synchronized (this) {//synchronized之间会互锁 会锁对象，让同时只有一个东西运行
            if (oldone != null) {
                this.polledNumber.remove(oldone);
                judgePollerResultInterface delete = resMap.get(oldone);
                if (delete != null) {
                    delete.judgePollerResultFeekback(null);// todo  再想想 为了让等待中的判题一定有结果
                }
                resMap.remove(oldone);//最怕删的时候刚好被查询完，就很尴尬
            }

            this.polledNumber.add(newone);
            this.resMap.put(newone, j);//设置新的回调函数

            this.notify();
            return true;
        }
    }
}
