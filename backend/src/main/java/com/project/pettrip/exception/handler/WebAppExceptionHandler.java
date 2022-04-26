package com.project.pettrip.exception.handler;

import com.project.pettrip.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class WebAppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Problem problem = getProblem(ex, status);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private Problem getProblem(Exception ex, HttpStatus status) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Problem problem = new Problem(status.value(), LocalDateTime.now().format(formatter), ex.getMessage());
        return problem;
    }

}
