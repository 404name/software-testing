package com.juelunn.class01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.juelunn.class01.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
}
