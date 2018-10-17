package com.ypika.mp.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具
 * Created by yangchao on 17/12/19.
 */
public class LogUtil {

    /**
     * 获取调用log的类
     * @return
     */
    public static Logger getLogger(){
        return LoggerFactory.getLogger(getPreCallStack(LogUtil.class,"getLogger").getClassName());
    }

    /**
     * 获取指定类和方法的调用前一个堆栈
     * @param clazz
     * @param method
     * @return
     */
    public static StackTraceElement getPreCallStack(Class<?> clazz,String method){
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        String clazzMethod = clazz.getName()+method;
        boolean target = false;
        StackTraceElement preStack = null;
        for(StackTraceElement stack:stacks){
            if(target){
                preStack = stack;
                break;
            }
            if((stack.getClassName()+stack.getMethodName()).contains(clazzMethod)){
                target = true;
            }
        }
        if(preStack==null){
            preStack = stacks[0];
        }
        return preStack;
    }
}
