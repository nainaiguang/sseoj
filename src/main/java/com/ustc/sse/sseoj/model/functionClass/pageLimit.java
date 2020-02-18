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

    @Setter
    @Getter
    int page=0;

    @Setter
    @Getter
    int limit=0;

    public int getLimit_head() {
        this.limit_head=(page-1)*limit;
        return limit_head;
    }

}
