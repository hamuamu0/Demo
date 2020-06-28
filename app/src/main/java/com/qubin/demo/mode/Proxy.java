package com.qubin.demo.mode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * author：created by qub
 * date：2020/6/5 14
 * descri：
 */
public class Proxy implements InvocationHandler {
    private Object target;//要代理的真实对象
    public Proxy(Subject target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:" +proxy.getClass().getName());
        System.out.println("before...");
        method.invoke(target,args);
        System.out.println("after...");
        return null;
    }
}
