package com.etiqa.assesment.controller;

import com.etiqa.assesment.model.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    // region Success
    public <T> ResponseEntity<Response<T>> sendSuccessResponse(T data) {
        return sendSuccessResponse(data, "");
    }

    public <T> ResponseEntity<Response<T>> sendSuccessResponse(String message) {
        return sendSuccessResponse(null, message);
    }

    public <T> ResponseEntity<Response<T>> sendSuccessResponse(T data, String message) {
        String logMessage = buildLogMessage("Success", message, data);
        logger.info(logMessage);
        return ResponseEntity.ok(new Response<>("Success", message, data));
    }
    // endregion

    // region Error
    public <T> ResponseEntity<Response<T>> sendErrorResponse(String message, HttpStatus httpStatus) {
        return sendErrorResponse(null, message, httpStatus);
    }

    public <T> ResponseEntity<Response<T>> sendErrorResponse(T data, String message, HttpStatus httpStatus) {
        String logMessage = buildLogMessage("Error", message, data);
        logger.error(logMessage);
        return ResponseEntity.status(httpStatus).body(new Response<>("Error", message, data));
    }
    // endregion

    private <T> String buildLogMessage(String status, String message, T data) {
        StringBuilder sb = new StringBuilder();
        sb.append("Response: ");
        sb.append("{ status:").append(status);
        sb.append(", message:").append(message);
        sb.append(", data:").append(dataToString(data));
        sb.append("}");
        return sb.toString();
    }

    public  <T> String dataToString(T data){
        if (data == null) {
            return "null";
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return data.toString();
        }
    }
}
