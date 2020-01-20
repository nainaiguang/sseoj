package com.ustc.sse.sseoj;

import com.ustc.sse.sseoj.common.IDType;
import com.ustc.sse.sseoj.util.CreatId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SseojApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(CreatId.getSole_id(IDType.CourseID));
    }

}
