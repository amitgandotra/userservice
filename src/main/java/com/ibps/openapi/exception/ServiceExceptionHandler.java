package com.ibps.openapi.exception;

import com.ibps.openapi.api.ControllerV1;
import com.ibps.openapi.model.ErrorMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackageClasses = ControllerV1.class)
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Log log = LogFactory.getLog(ServiceExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    @ResponseBody ResponseEntity<ErrorMessage> handleServiceException(ServiceException ex) {
        return mapServiceExceptionToResponse(ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return mapBindingResultToResponse(ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return mapBindingResultToResponse(ex.getBindingResult());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody ResponseEntity<ErrorMessage> handleUnexpectedException(Throwable ex) {
        int serviceExceptionIndex = ExceptionUtils.indexOfThrowable(ex, ServiceException.class);
        if (serviceExceptionIndex != -1) {
            return mapServiceExceptionToResponse((ServiceException)ExceptionUtils.getThrowableList(ex).get(serviceExceptionIndex));
        }

        log.warn("Unexpected exception occurred", ex);

        ErrorMessage error = new ErrorMessage();
        error.setCode(Errors.INTERNAL_SERVER_ERROR.code);
        error.setMessage(Errors.INTERNAL_SERVER_ERROR.message);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private ResponseEntity<Object> mapBindingResultToResponse(BindingResult bindingResult) {
        final StringBuilder error = new StringBuilder("Bad request. Details:\n");
        bindingResult.getFieldErrors().stream()
                .forEach(fe -> error.append(fe.getField()).append(": ").append(fe.getDefaultMessage()).append("\n"));

        bindingResult.getGlobalErrors().stream()
                .forEach(ge -> error.append(ge.getObjectName()).append(": ").append(ge.getDefaultMessage()).append("\n"));

        ErrorMessage errorResponse = new ErrorMessage();
        errorResponse.setCode(Errors.BAD_REQUEST.code);
        errorResponse.setMessage(error.toString());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ResponseEntity<ErrorMessage> mapServiceExceptionToResponse(ServiceException ex) {
        ErrorMessage error = new ErrorMessage();
        error.setCode(ex.code);
        error.setMessage(ex.getMessage());

        log.info(error);

        return ResponseEntity.badRequest().body(error);
    }
}