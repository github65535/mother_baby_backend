package com.ypika.mp.common.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 数据校验
 * Created by yangchao on 17/11/7.
 */
public class StrUtil {

    private static final Pattern NUMBER_OR_CHAR_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");

    /**
     * 对象为空校验
     *
     * @param obj 需要验证的类型
     * @return 是否是空
     */
    public static boolean isNull(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
            return true;
        }
        if (obj instanceof String && ((String) obj).trim().isEmpty()) {
            return true;
        }
        if (obj instanceof String && "null".equals(obj)) {
            return true;
        }
        if (obj instanceof StringBuffer && ((StringBuffer) obj).length() == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).size() == 0) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 对象非空校验
     *
     * @param obj 需要验证的类型
     * @return 是否非空且
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 对象非空校验（不能为0）
     *
     * @param obj 需要验证的类型
     * @return 是否非空且不为0
     */
    public static boolean isNotNullAndNo0(Object obj) {
        boolean flag = !isNull(obj);
        if (flag) {
            if (obj instanceof Integer && (Integer) obj == 0) {
                return false;
            }
        }
        return flag;
    }

    /**
     * 非空判断
     *
     * @param object 参数
     * @return 结果
     */
    public static Object getNotNullParam(Object object) {
        return StrUtil.isNotNull(object) ? object : "";
    }

    /**
     * 获取一定长度的随机字符串
     *
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStrByLen(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 根据答题剩余秒数计算得分
     *
     * @param seconds
     * @return
     */
    public static Integer matchScoreBySeconds(Integer seconds) {
        if (seconds <= 2) {
            return 10;
        } else if (seconds <= 4) {
            return 8;
        } else if (seconds <= 6) {
            return 6;
        } else if (seconds <= 8) {
            return 4;
        } else {
            return 2;
        }

//        if (Objects.equals(seconds, 0)) return 0;
//        return ((seconds + 1) / 2) * 2;
    }

    /**
     * 字符串有多少汉字
     *
     * @param str 字符串
     * @return 像素长度
     */
    public static Integer countChinaByString(String str) {
        int count = 0;
        String regex = "[\u4e00-\u9fa5]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 0
     *
     * @param str 字符串
     * @return 像素长度
     */
    public static Integer pxByMiddle(Integer backgroundImgWide, String str, Integer size) {
        AssertUtil.isNotNull(backgroundImgWide);
        AssertUtil.isNotNull(size);
        Integer nickNameCount = countChinaByString(str);
        int length = str.length();
        length = length - nickNameCount > 0 ? length - nickNameCount : 0;
        int temp = nickNameCount == 0 ? size / 2 + 5 : size / 2 + 2;
        return (backgroundImgWide - nickNameCount * size - length * temp) / 2;
    }

    /**
     * 筛选答案,样例数据 ['A']
     * ['B', 'C', 'E']
     *
     * @return
     */
    public static String filterSingleChoice(String str) {
        if (StrUtil.isNull(str)) {
            return null;
        }
        if (str.length() == 1) {
            return str;
        } else if (str.length() > 1) {
            str = str.replaceAll("'", "");
            str = str.replaceAll(" ", "");
            //str = str.substring(1,str.length()-1);
            return str;
        }
        return str;
    }

    public static String filterMiddileContent(String str) {
        if (StrUtil.isNull(str)) {
            return null;
        }
        return str.substring(2, str.length() - 2);
    }

    public static String filterWord(String str) {
        if (StrUtil.isNull(str)) {
            return null;
        }
        String[] re = new String[]{"<p>", "</p>", "</br>"};
        for (String s : re) {
            str.replace(s, "");
        }
        return str;
    }

    /**
     * 判断pk参数是否为空，返回Long类型值
     *
     * @param object 参数
     * @return 返回结果
     */
    public static Long LongByPkParam(Object object) {
        if (object instanceof Long) {
            return (Long) object;
        }
        return (isNotNull(object)) ? Long.valueOf((String) object) : null;
    }

    /**
     * 根据redis
     * 判断pk参数是否为空，返回Long类型值
     *
     * @param object 参数
     * @return 返回结果
     */
    public static Long LongByRedis(Object object) {
        if (isNull(object)) {
            return null;
        }
        if (object instanceof Integer) {
            return ((Integer) object).longValue();
        }
        if (object instanceof Long) {
            return (Long) object;
        }
        return Long.valueOf(String.valueOf(object));
    }

    /**
     * 判断pk参数是否为空，返回Boolean类型值
     *
     * @param object 参数
     * @return 返回结果
     */
    public static Boolean BooleanByPkParam(Object object) {
        if (object instanceof Boolean) {
            return (Boolean) object;
        }
        return (isNotNull(object)) ? Boolean.valueOf((String) object) : null;
    }

    /**
     * 判断pk参数是否为空，返回Integer类型值
     *
     * @param object 参数
     * @return 返回结果
     */
    public static Integer IntegerByPkParam(Object object) {
        if (object instanceof Integer) {
            return (Integer) object;
        }
        return (isNotNull(object)) ? Integer.valueOf((String) object) : null;
    }

    /**
     * 判断pk参数是否为空，返回String类型值
     *
     * @param object 参数
     * @return 返回结果
     */
    public static String StringByPkParam(Object object) {
        if (object instanceof String) {
            return (String) object;
        }
        return (isNotNull(object)) ? String.valueOf(object) : null;
    }

    /**
     * 比较两个list是否相等
     *
     * @param list1 list1
     * @param list2 lList2
     * @return 返回结果
     */
    public static boolean compareList(List<Long> list1, List<Long> list2) {
        Boolean result = false;
        Collections.sort(list1);
        Collections.sort(list2);
        if (list1.equals(list2)) {
            result = true;
        }
        return result;
    }

    /**
     * 字符串过长自己动拼接省略号
     *
     * @param str    字符串
     * @param length 截取长度
     * @return 处理后字符
     */
    public static String getMoreStr(String str, Integer length) {
        if (str.length() > length) {
            str = str.substring(0, length) + "...";
        }
        return str;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StrUtil.isNull(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    /**
     * 拼装唯一索引
     *
     * @param params 索引参数
     * @return 唯一索引
     */
    public static String getUnique(Object... params) {
        AssertUtil.isNotNull(params);
        return Arrays.stream(params).map(String::valueOf).collect(Collectors.joining("_"));
    }

    /**
     * 拼装题目ids字符串
     *
     * @param questionIds 题目集合
     * @return 唯一索引
     */
    public static String getQuestionIdsStr(List<Long> questionIds) {
        AssertUtil.isNotNull(questionIds);
        return questionIds.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    /**
     * 准考证号校验（ps:字母或数字组成）
     * @param str
     * @return
     */
    public static boolean examinationNumberVerification(String str){
        Matcher isNum = NUMBER_OR_CHAR_PATTERN.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
