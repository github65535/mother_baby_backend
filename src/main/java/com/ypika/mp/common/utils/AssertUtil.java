package com.ypika.mp.common.utils;

import com.ypika.mp.domain.exception.ServerStatus;
import com.ypika.mp.domain.exception.UserDefinedException;
import org.springframework.validation.BindingResult;

/**
 * Created by yangchao on 17/11/7.
 */
public class AssertUtil {

    public static boolean isNotNull(Object obj) {
        if (StrUtil.isNull(obj)) {
            throw new UserDefinedException(ServerStatus.PARAM_EMPTY);
        }

        return true;
    }

    /**
     * 资源不为空
     * @param obj
     * @return
     */
    public static boolean resultNotNull(Object obj) {
        if (StrUtil.isNull(obj)) {
            throw new UserDefinedException(ServerStatus.NO_EXIST);
        }

        return true;
    }
    public static boolean resultNotNull(Object obj,String msg) {
        if (StrUtil.isNull(obj)) {
            throw new UserDefinedException(ServerStatus.NO_EXIST, msg);
        }

        return true;
    }

    public static boolean isNotNull(Object obj, String msg) {
        if (StrUtil.isNull(obj)) {
            throw new UserDefinedException(ServerStatus.PARAM_EMPTY, msg);
        }

        return true;
    }

    public static void bindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserDefinedException(ServerStatus.DEFAULT_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());
        }
    }
}
