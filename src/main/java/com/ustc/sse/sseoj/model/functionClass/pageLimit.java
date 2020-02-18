package com.ustc.sse.sseoj.model.functionClass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/17 16:03
 */
public class pageLimit {


    int limit_head=0;


    int limit_tail=0;

    @Setter
    @Getter
    int page=0;

    @Setter
    @Getter
    int limit=0;

    public int getLimit_head() {
        this.limit_head=page*limit - limit + 1;
        return limit_head;
    }

    public int getLimit_tail() {
        this.limit_tail=page*limit;
        return limit_tail;
    }
}
