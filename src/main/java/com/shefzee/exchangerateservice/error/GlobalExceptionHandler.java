package com.shefzee.exchangerateservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;


@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(HttpServletRequest request,
                                                                     BusinessException businessException,
                                                                     HttpSession session,
                                                                     Principal principal){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMessages(businessException.getErrorMessages())
                .httpStatus(businessException.getHttpStatus())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(businessException.getHttpStatus()));
    }

}
