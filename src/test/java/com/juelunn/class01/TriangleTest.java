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

    @Test
    @DisplayName("输入错误")
    void parameters_error_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(0, 4, 5);
        assertEquals("输入错误", type);
    }
    @Test
    @DisplayName("非三角形")
    void not_fit_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(2, 4, 10);
        assertEquals("非三角形", type);
    }
    @Test
    @DisplayName("等边三角形")
    void scale_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(6, 6, 6);
        assertEquals("等边三角形", type);
    }
    @Test
    @DisplayName("等腰三角形")
    void scalene_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(4, 4, 6);
        assertEquals("等腰三角形", type);
    }
    @Test
    @DisplayName("不等边三角形")
    void common_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(3, 4, 5);
        assertEquals("不等边三角形", type);
    }

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
}
