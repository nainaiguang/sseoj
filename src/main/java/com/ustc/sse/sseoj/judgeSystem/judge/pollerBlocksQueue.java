package com.ustc.sse.sseoj.judgeSystem.judge;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Set;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/9 14:38
 * 轮询器
 */

public class pollerBlocksQueue extends Thread{

    private static pollerBlocksQueue pollers;
    public Set<Integer> polledNumber;//线程阻塞队列
    private pollerBlocksQueue()
    {
        this.polledNumber=new HashSet<>();
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
    public synchronized void run() {
        super.run();
           while(true)
           {
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
                   System.out.println(polledNumber.toString());
                  //todo 做事
               }
           }
    }

    public synchronized boolean addpooler(Integer oldone, Integer newone)
    {
        if(oldone!=null)
        {
            this.polledNumber.remove(oldone);
        }
        this.polledNumber.add(newone);
        this.notify();
        return true;
    }
}
