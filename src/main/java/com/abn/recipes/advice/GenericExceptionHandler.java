package com.abn.recipes.advice;

import com.abn.recipes.dto.ErrorResponse;
import com.abn.recipes.exception.RecipeServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GenericExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("600");
        errorResponse.setErrorCode("Internal Server Error.");
        log.error("Internal server error.", e);
        return errorResponse;
    }

    @ExceptionHandler(RecipeServiceException.class)
    public ErrorResponse handleServiceException(RecipeServiceException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(e.getErrorCode());
        errorResponse.setErrorDescription(e.getErrorDescription());
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleInvalidParam(MethodArgumentNotValidException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("700");
        StringBuilder sb = new StringBuilder();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            sb.append(objectError.getCodes());
        }
        errorResponse.setErrorDescription(sb.toString());
        return errorResponse;
    }
}
