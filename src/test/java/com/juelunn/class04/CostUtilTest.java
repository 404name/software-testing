package com.juelunn.class04;

import com.juelunn.class02.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: softTest1
 * @description: 决策表分析
 * @author: CTGU_LLZ(404name)
 * @create: 2022-04-10 13:55
 **/
class CostUtilTest {
    @DisplayName("决策表测试用例")
    @ParameterizedTest
    @CsvSource({
            "2022-02-26 14:00:00,2022-02-26 14:00:10,false,false,0.05",
            "2022-03-29 02:00:00,2022-03-29 03:00:20,false,false,5.1",
            "2022-10-25 02:00:34,2022-10-25 02:59:22,false,false,4.9",
            "2022-10-25 01:59:22,2022-10-27 12:00:20,false,false,0.0",
            "2022-02-26 14:00:00,2022-02-26 15:18:10,true,false,0.95",
            "2022-03-29 01:59:00,2022-03-29 03:16:10,true,false,0.9",
            "2022-10-25 02:58:00,2022-10-25 02:16:10,true,false,0.0",
            "2022-10-25 01:58:00,2022-10-25 02:16:10,false,true,6.9",
            "2022-02-26 14:00:00,2022-02-26 15:30:30,false,true,14.1",
            "2022-03-29 01:59:00,2022-03-29 03:16:10,false,true,12.8",
    })
    void decideTest(String date1,String date2,boolean startFlag,boolean endFlag,double expected) {
        assertEquals(expected,CostUtil.getTelCost(CostUtil.calculateTimeSpan(CostUtil.StrToDate(date1),startFlag,CostUtil.StrToDate(date2),endFlag)));
    }
}
