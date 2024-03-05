package com.etiqa.assesment.exceptions;

import com.etiqa.assesment.controller.CustomerController;
import com.etiqa.assesment.model.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @ExceptionHandler(value=NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex){
        logger.error("NotFoundException occurred: {}", ex.getMessage());
        ApiError error = new ApiError(400,ex.getMessage(),new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error("HttpMessageNotReadableException occurred: {}", ex.getMessage());

        ApiError error = new ApiError(400, ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<ApiError> handleServiceException(ServiceException ex) {
        logger.error("ServiceException occurred: {}", ex.getMessage());

        ApiError error = new ApiError(400, ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError>handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : errorMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        ApiError error = new ApiError(400, stringBuilder.toString(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
