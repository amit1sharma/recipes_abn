package com.abn.recipes.advice;

import com.abn.recipes.dto.ErrorResponse;
import com.abn.recipes.exception.RecipeServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

/**
 * This class is responsible for handling all error responsed
 */
@RestControllerAdvice
@Slf4j
public class GenericExceptionHandler {

    /**
     * Handles unknown exceptions
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("600");
        errorResponse.setErrorCode("Internal Server Error.");
        log.error("Internal server error.", e);
        return errorResponse;
    }

    /**
     * Handles custom business level exceptions
     * @param e
     * @return
     */
    @ExceptionHandler(RecipeServiceException.class)
    public ErrorResponse handleServiceException(RecipeServiceException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(e.getErrorCode());
        errorResponse.setErrorDescription(e.getErrorDescription());
        log.error("Error : {}, Description : {}", e.getErrorCode(), e.getErrorDescription());
        return errorResponse;
    }

    /**
     * Handles invalid request parameters exceptions.
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidParam(MethodArgumentNotValidException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("700");
        StringBuilder sb = new StringBuilder();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            sb.append(objectError.getCodes()[0]+" : " + objectError.getDefaultMessage());
        }
        errorResponse.setErrorDescription(sb.toString());
        return errorResponse;
    }
}
