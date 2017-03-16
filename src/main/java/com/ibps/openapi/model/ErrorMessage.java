package com.ibps.openapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private Long code = null;
    private String message = null;

    public Long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Error {\n");

        sb.append("  code: ").append(code).append("\n");
        sb.append("  message: ").append(message).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
