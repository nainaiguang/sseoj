package com.ustc.sse.sseoj.util;

import com.ustc.sse.sseoj.Data.IDType;

import java.util.Calendar;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/1/20 9:48
 * 创造ID类
 */

//创造一个不会重复的id 根据参数的不同创造不同类型的id
public class CreatId {
    public static final String getSole_id(IDType idType)
    {
        Calendar Cld = Calendar.getInstance();
        int YY = Cld.get(Calendar.YEAR) ;
        int MM = Cld.get(Calendar.MONTH)+1;
        int DD = Cld.get(Calendar.DATE);
        int HH = Cld.get(Calendar.HOUR_OF_DAY);
        int mm = Cld.get(Calendar.MINUTE);
        int SS = Cld.get(Calendar.SECOND);
        int MI = Cld.get(Calendar.MILLISECOND);

        switch (idType)
        {
            case courseID:
                return IDType.courseID.toString()+ YY+""+MM+""+DD+""+HH+""+mm+""+SS+""+MI;
            case homeworkID:
                return IDType.homeworkID.toString()+ YY+""+MM+""+DD+""+HH+""+mm+""+SS+""+MI;
            case questionID:
                return IDType.questionID.toString()+ YY+""+MM+""+DD+""+HH+""+mm+""+SS+""+MI;
            case answerID:
                return IDType.answerID.toString()+ YY+""+MM+""+DD+""+HH+""+mm+""+SS+""+MI;
            case answercaseID:
                return IDType.answercaseID.toString()+ YY+""+MM+""+DD+""+HH+""+mm+""+SS+""+MI;
        }

        return YY+""+MM+""+DD+""+HH+""+mm+""+SS+""+MI;

    }
}
