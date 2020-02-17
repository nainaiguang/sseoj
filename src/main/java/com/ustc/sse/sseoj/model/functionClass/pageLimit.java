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
    int page_number=0;

    @Setter
    @Getter
    int page_limit=0;

    public int getLimit_head() {
        this.limit_head=page_number*page_limit;
        return limit_head;
    }

    public int getLimit_tail() {
        this.limit_tail=page_number*page_limit+page_limit;
        return limit_tail;
    }
}
