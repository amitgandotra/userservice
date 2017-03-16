package com.ibps.openapi.exception;

import com.ibps.openapi.exception.Errors;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public final long code;

    public ServiceException(Errors error) {
        super(error.message);
        this.code = error.code;
    }
    
    public ServiceException(Errors error, Throwable cause) {
        super(error.message, cause);
        this.code = error.code;
    }

    public ServiceException(Errors error, String additionalContext) {
        super(error.message + additionalContext);
        this.code = error.code;
    }
    
    public ServiceException(Errors error, String additionalContext, Throwable cause) {
        super(error.message + additionalContext, cause);
        this.code = error.code;
    }
}
