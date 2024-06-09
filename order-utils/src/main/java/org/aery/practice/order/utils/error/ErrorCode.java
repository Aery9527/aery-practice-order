package org.aery.practice.order.utils.error;

public enum ErrorCode {
    UNKNOW(9000),
    UNSUPPORT_ACTION(9001),
    INCORRECT_STATE(9002),
    VERIFY_ERROR(9003),
    ;
    public final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public GlobalException thrown() throws GlobalException {
        throw new GlobalException(this);
    }

    public GlobalException thrown(Throwable cause) throws GlobalException {
        throw new GlobalException(this, cause);
    }

    public GlobalException thrown(String message) throws GlobalException {
        throw new GlobalException(this, message);
    }

    public GlobalException thrown(String message, Throwable cause) throws GlobalException {
        throw new GlobalException(this, message, cause);
    }

    @Override
    public String toString() {
        return "ErrorCode{" + code + "|" + name() + '}';
    }

    public int getCode() {
        return code;
    }

}
