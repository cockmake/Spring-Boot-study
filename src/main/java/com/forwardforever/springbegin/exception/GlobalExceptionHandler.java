package com.forwardforever.springbegin.exception;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.forwardforever.springbegin.domain.dto.Result;
import com.forwardforever.springbegin.utils.HttpCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> constraintViolationExceptionHandle(SQLIntegrityConstraintViolationException e) {
        return Result.failure("信息关联失败");
    }

    @ExceptionHandler({SignatureVerificationException.class})
    public Result<String> signatureVerificationExceptionHandle(SignatureVerificationException e) {
        return new Result<>(HttpCodeEnum.UNAUTHORIZED.getCode(), "Token验证失败", HttpCodeEnum.UNAUTHORIZED.getMessage());
    }
}
