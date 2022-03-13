package com.juelunn.class01;

/**
 * @program: softTest1
 * @description:
 * @author: CTGU_LLZ(404name)
 * @create: 2022-03-13 13:28
 **/

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 *
 * @author tiger
 * @date 2021年3月4日-下午8:43:13
 * @description 三角形测试用例
 */
class TriangleTest {
    @ParameterizedTest
    @DisplayName(value="覆盖性测试")
    @CsvSource({
            "0,1,2,输入错误",
            "1,0,2,输入错误",
            "1,2,0,输入错误",
            "1,2,3,非三角形",
            "1,3,2,非三角形",
            "3,1,2,非三角形",
            "3,3,3,等边三角形",
            "3,3,4,等腰三角形",
            "3,4,3,等腰三角形",
            "4,3,3,等腰三角形",
            "3,4,5,不等边三角形",
    })
    void paramTriangle(int a, int b,int c,String expected) {
        Triangle triangle = new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }

    @ParameterizedTest
    @DisplayName(value="边界用例测试")
    @CsvSource({
            "1,50,50,等腰三角形",
            "2,50,50,等腰三角形",
            "99,50,50,等腰三角形",
            "100,50,50,非三角形",
            "50,1,50,等腰三角形",
            "50,2,50,等腰三角形",
            "50,99,50,等腰三角形",
            "50,100,50,非三角形",
            "50,50,1,等腰三角形",
            "50,50,2,等腰三角形",
            "50,50,99,等腰三角形",
            "50,50,100,非三角形",
            "50,50,50,等边三角形",
    })
    void paramTriangle1(int a, int b,int c,String expected) {
        Triangle triangle = new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
}
