package com.ibps.openapi.exception;

public enum Errors {

                INTERNAL_SERVER_ERROR( -1, "Unexpected error occurred. "),
                     NO_ACCESS_TO_APP( -2, "User does not have access to the requested application. "),
                           BAD_REQUEST(-3, "Request was not valid"),
                        USER_NOT_FOUND(-4, "User Not Found"),
                   USER_ALREADY_EXISTS(-5, "User Already Exists"),

    ;


    public final int code;
    public final String message;
    Errors(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
