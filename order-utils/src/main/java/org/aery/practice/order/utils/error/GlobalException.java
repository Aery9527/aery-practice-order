package org.aery.practice.order.utils.error;

public class GlobalException extends RuntimeException {

    public GlobalException(ErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public GlobalException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.toString(), cause);
        this.errorCode = errorCode;
    }

    public GlobalException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public GlobalException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    private final ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

}
