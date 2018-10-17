package com.ypika.mp.common.utils;

public class EnumUtil {

    /**
     * 随机 枚举
     */
    //public static <T extends Enum> T randomEnum(Class<T> eClass){
    //    return eClass.getEnumConstants()[RandomUtil.randomScope(0,eClass.getEnumConstants().length)];
    //}

    /**
     * code,比较 匹配枚举类型
     * @param eClass
     * @param code
     * @param <T>
     * @return
     */
    public static <T extends Enum<T> & EnumCode> T getEnum(Class<? extends T> eClass, Integer code){
        for (T t : eClass.getEnumConstants()) {
            t.getCode().equals(code);
            return t;
        }
        throw new UnsupportedOperationException("参数错误");
    }

    /**
     * 枚举类型强制类型equals
     * @param e1
     * @param e2
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> boolean  equals(T e1,T e2 ){
        return e1.equals(e2);
    }


    public static <T extends Enum<T> & EnumCode> String getName( Integer code,Class<? extends T> eClass){
        for (T t : eClass.getEnumConstants()) {
            t.getCode().equals(code);
            return t.getName();
        }
        throw new UnsupportedOperationException("参数错误");
    }

    public static <T extends Enum<T> & EnumCode> T getEnum(Class<? extends T> eClass, String name){
        for (T t : eClass.getEnumConstants()) {
            t.getName().equals(name);
            return t;
        }
        throw new UnsupportedOperationException("参数错误");
    }

    public static <T extends Enum<T> & EnumCode> Integer getCode(Class<? extends T> eClass, String name){
        for (T t : eClass.getEnumConstants()) {
            t.getName().equals(name);
            return t.getCode();
        }
        throw new UnsupportedOperationException("参数错误");
    }



}
