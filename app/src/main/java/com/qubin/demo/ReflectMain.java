package com.qubin.demo;

import java.lang.reflect.Field;

/**
 * author：created by qub
 * date：2020/3/30 20
 * descri：
 */
public class ReflectMain {
    @TestInteface(12)
    public int age;

    public static void main(String[] args){
        ReflectMain main = new ReflectMain();
        TestInteface testInteface = null;

        try {
            Class clazz = main.getClass();
            Field field = clazz.getField("age");
            testInteface = field.getAnnotation(TestInteface.class);
            System.out.println(testInteface.value());
        }catch (NoSuchFieldException e){
            System.out.println("no such field");
        }

    }
}
