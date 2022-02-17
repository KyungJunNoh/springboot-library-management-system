package com.project.library.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll // 테스트 시작 전 제일 우선으로 실행되는 어노테이션
    static void beforeAll(){
        System.out.println("beforeAll Annotation 호출");
        System.out.println();
    }

    @AfterAll // 테스트 종료 후 제일 마지막으로 실행되는 어노테이션
    static void afterAll(){
        System.out.println("afterAll Annotation 호출");
        System.out.println();
    }

    @BeforeEach // 테스트 시작 전 실행되는 어노테이션 ( 실행순서 BeforeAll -> BeforeEach )
    void beforeEach(){
        System.out.println("beforeEach Annotation 호출");
        System.out.println();
    }

    @AfterEach // 테스트 종료 후 실행되는 어노테이션 ( 실행순서 afterEach -> afterAll )
    void afterEach(){
        System.out.println("afterEach Annotation 호출");
        System.out.println();
    }

    @Test
    void test1(){
        System.out.println("test1 시작");
        System.out.println();
    }

    @Test
    @DisplayName("DisplayName Use : test2") // 테스트의 이름을 설정하는 어노테이션
    void test2(){
        System.out.println("test2 시작");
        System.out.println();
    }

    @Test
    @Disabled // 통합 테스트를 무시하는 어노테이션
    void test3(){
        System.out.println("test3 시작");
        System.out.println();
    }
}
