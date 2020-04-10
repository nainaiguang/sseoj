package com.ustc.sse.sseoj.judgeSystem;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 19:15
 */

@Component
public class constant {

    public static String ftpServer="39.108.177.133";

    public static String ftpUser="nainaiguang";
    public static String ftpPassword="Guang110507";
    public static String ftpWorkPlace="/home/judge/data/";

    public static String judgeMysqlServer="39.108.177.133";
    public static String judgeMysqlPort="3306";
    public static String judgeMysqlUser="root";
    public static String judgeMysqlPassword="Guang110507";

    public static String cacheFileLocation="data\\cacheFile\\";

    public static int threadnumber=30;

    public static int pollerSleepTime=2000;//轮询器工作3循环时间

}
