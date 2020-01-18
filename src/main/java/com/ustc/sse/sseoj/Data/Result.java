package com.ustc.sse.sseoj.Data;

import lombok.Getter;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */
/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    private Result() {
    }

    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Success success = (Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        }
        else if(this instanceof Result.Fail)
        {
            Fail fail=(Fail)this;
            return "Fail[exception=" + fail.getReason().toString() + "]";
        }else if (this instanceof Result.Error) {
            Error error = (Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
     }

    // Success sub-class
    public final static class Success<T> extends Result {
        @Getter
        private T data;

        public Success(T data) {
            this.data = data;
        }


    }

    // Error sub-class
    public final static class Error extends Result {
        @Getter
        private Exception error;

        public Error(Exception error) {
            this.error = error;
        }


    }
    // Fail sub-class
    public final static class Fail extends Result {
        @Getter
        private String reason;

        public Fail(String reason) {
            this.reason = reason;
        }

    }
}
