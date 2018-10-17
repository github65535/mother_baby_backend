package com.ypika.mp.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.ypika.mp.domain.exception.UserDefinedException;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by yangchao on 17/12/19.
 */
public class JsonUtil {


    public static String mapToJson(Map map){
        if (StrUtil.isNull(map)) {
            return null;
        }
        String str = null;
        try {
            str = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }
    public static Map objToMap(Object obj){
        return JsonUtil.jsonToMap(JsonUtil.objectToStr(obj));
    }
    public static Map<String,Object> jsonToMap(String json){
        if(StrUtil.isNull(json)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String,Object> userData = mapper.readValue(json, Map.class);
            return userData;
        } catch (IOException e) {
            e.printStackTrace();
//            throw new UserDefinedException(ServerStatus.CODE_INFO_EXCEPTION,"参数错误");
            LogUtil.getLogger().warn("jsonToMap参数错误,args:"+json);
            return null;
        }
    }
    /**
     * json映射List map 实体
     * @param json josn串
     * @return
     */
    public static List<Map<String,Object>> toListMap(String json){
        if (StrUtil.isNull(json)) {
            return null;
        }
        try {
            return new ObjectMapper().readValue(json, ofClassType(ArrayList.class, Map.class));
        }catch (Exception e){
            throw new UserDefinedException("json映射实体异常 : "+ e);
        }
    }
    public static String objectToStr(Object object){
        if (StrUtil.isNull(object)) {
            return null;
        }
        if (object instanceof String){
            return object.toString();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 某元素param是否存在于params集合中
     * @param param
     * @param params
     * @return
     */
    public static boolean paramInPrams(String param,String ...params ){
        if(StrUtil.isNull(param)) {
            return false;
        }
        return Arrays.asList(params).contains(param);
    }

    /**
     * 某元素是否在某些元素中
     * @param t
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> boolean paramInPrams(T t,T ...ts){
        if(t==null || ts==null || ts.length<1){
            return false;
        }
        return Arrays.asList(ts).contains(t);
    }


    public static List<Object> jsonToList(String json){
        if (StrUtil.isNull(json)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = getCollectionType(ArrayList.class, Object.class);
        try {
            List<Object> list =  (List<Object>)mapper.readValue(json, javaType);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UserDefinedException("参数错误");
        }
    }

    public static <T>List jsonToList(String json,Class<T> clazz){
        if (StrUtil.isNull(json)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        try {
            List<T> list =  (List<T>)mapper.readValue(json, javaType);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UserDefinedException("参数错误");
        }
    }
    /**
      * 获取泛型的Collection Type
      * @param collectionClass 泛型的Collection
      * @param elementClasses 元素类
      * @return JavaType Java类型
      */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /***
     * convert map to object ,see setObjectValue(obj, map)
     * @param map : key是对象的成员变量,其value就是成员变量的值
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     */
    public static Object convertMap2Obj(Map<String, Object> map,Class clazz){
            //throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException, IllegalArgumentException{
        if(StrUtil.isNull(map)){
            return null;
        }
        Object obj = null;
        try {
            obj=clazz.newInstance();
            setObjectValue(obj, map);
        }catch (Exception e){
            throw new UserDefinedException("Map2Object Fail");
        }
        /*for(Iterator it=map.entrySet().iterator();it.hasNext();){
            Map.Entry<String, Object> entry=(Map.Entry<String, Object>)it.next();
            String key=entry.getKey();
            Object val=entry.getValue();
        }*/
        return obj;
    }

    /*** 
     * 利用反射设置对象的属性值. 注意:属性可以没有setter 方法. 
     *
     * @param obj
     * @param params
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setObjectValue(Object obj, Map<String, Object> params)
            throws SecurityException,
            IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        if(StrUtil.isNull(obj)){
            return;
        }
        if (StrUtil.isNull(params)) {
            return;
        }
        Class<?> clazz = obj.getClass();
        for (Iterator it = params.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String key = entry.getKey();
            Object propertyValue = entry.getValue();
            if (StrUtil.isNull(propertyValue)) {
                continue;
            }
            Field name = getSpecifiedField(clazz, key);
            if (name != null) {
                name.setAccessible(true);
                name.set(obj, propertyValue);
            }
        }

    }

    /***
     * Get Specified Field
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Field getSpecifiedField(Class<?> clazz, String fieldName) {
        Field f = null;
        if (StrUtil.isNull(clazz)) {
            return null;
        }
        try {
            f = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            /*
             * may be null if it is
             * Object .
             */
            return getSpecifiedField(clazz.getSuperclass(), fieldName);
            // e.printStackTrace();
        }
        return f;
    }
    /**
     * 拷贝对象属性
     * @param src
     * @param target
     */
    public static void copyProperties(Object src, Object target){
        BeanUtils.copyProperties(src, target);
    }

    /**
     * 获取集合转换类
     * @param collectionClass 集合
     * @param elementClass 元素类
     * @return
     */
    public static CollectionType ofClassType(Class<? extends Collection> collectionClass, Class<?> elementClass){
        return new ObjectMapper().getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
    }

    /**
     * 数组转换成字符串
     * @param longs
     * @return
     */
    public static String arrayToString(List<Long> longs){
        if (StrUtil.isNull(longs)) {
            return "";
        }
        StringBuilder stringBuffer = new StringBuilder();
        for (Long l : longs) {
            stringBuffer.append(l).append(",");
        }
        return stringBuffer.substring(0,stringBuffer.length()-1);
    }

    /**
     * 字符串 2 long类型list
     * @param ids
     * @return
     */
    public static List<Long> stringToList(String ids){
        if (StrUtil.isNull(ids)) {
            return null;
        }
        List<String> list = Arrays.asList(ids.split(","));
        List<Long> longs = new ArrayList<>();
        for (String s : list){
            longs.add(Long.valueOf(s));
        }
        return longs;
    }

    /**
     * 字符串集合转成数组
     * @param list
     * @return
     */
    public static String[] joinListToArray(List<String> list){
        if (StrUtil.isNull(list)) {
            return null;
        }
        return list.toArray(new String[0]);
    }
}
