package com.ypika.mp.common.utils;

import com.ypika.mp.common.ConstantConfig;

/**
 * Created by zhangbo on 2018/9/29.
 */
public class PhoneUtil {

    /**
     * 手机号校验
     *          规则： 长度11位，以“1”开头
     * @param phoneNum 手机号
     * @return 校验状态
     */
    public static Boolean verifyPhoneNum(String phoneNum) {
        AssertUtil.isNotNull(phoneNum);
        Boolean result = true;
        if (phoneNum.length() != ConstantConfig.ELEVEN.intValue()) {
            result = false;
        } else {
            if (!phoneNum.startsWith(String.valueOf(ConstantConfig.ONE.intValue()))) {
                result = false;
            }
        }
        return result;
    }
}
