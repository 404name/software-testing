package com.juelunn.class02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: softTest1
 * @description:
 * @author: CTGU_LLZ(404name)
 * @create: 2022-03-20 13:35
 **/
class TriangleTest {
    @DisplayName("三角形一般测试用例")
    @ParameterizedTest
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
    void fileTest(int a,int b,int c, String expected) {
        Triangle triangle = new Triangle();
        assertEquals(expected,triangle.classify(a,b,c));
    }


    @DisplayName("三角形健壮性边界测试用例")
    @ParameterizedTest
    @CsvSource({
            "0,50,50,输入错误",
            "1,50,50,等腰三角形",
            "2,50,50,等腰三角形",
            "99,50,50,等腰三角形",
            "100,50,50,非三角形",
            "101,50,50,输入错误",
            "50,0,50,输入错误",
            "50,1,50,等腰三角形",
            "50,2,50,等腰三角形",
            "50,99,50,等腰三角形",
            "50,100,50,非三角形",
            "50,101,50,输入错误",
            "50,50,0,输入错误",
            "50,50,1,等腰三角形",
            "50,50,2,等腰三角形",
            "50,50,99,等腰三角形",
            "50,50,100,非三角形",
            "50,50,101,输入错误",
            "50,50,50,等边三角形",
    })
    void strongfileTest(int a,int b,int c, String expected) {
        Triangle triangle = new Triangle();
        assertEquals(expected,triangle.classify(a,b,c));
    }

    @DisplayName("三角形最坏情况测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形最坏情况测试用例.csv",numLinesToSkip = 0,encoding = "UTF-8")
    void worstfileTest(int a,int b,int c, String expected) {
        Triangle triangle = new Triangle();
        assertEquals(expected,triangle.classify(a,b,c));
    }

    @DisplayName("三角形健壮性最坏情况测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形健壮性最坏情况测试用例.csv",numLinesToSkip = 0,encoding = "UTF-8")
    void strong_worstfileTest(int a,int b,int c, String expected) {
        Triangle triangle = new Triangle();
        assertEquals(expected,triangle.classify(a,b,c));
    }


}
