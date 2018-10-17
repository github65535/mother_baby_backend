package com.ypika.mp.domain.exception;

/**
 * Created by yangchao on 17/11/18.
 */
public abstract class GlobalException extends RuntimeException {

    public abstract Integer getStatus();
    public abstract String getError();
    @Override
    public abstract String getMessage();

}
