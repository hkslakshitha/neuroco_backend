package com.wixis360.neuroco_backend.advice;

import com.wixis360.neuroco_backend.dto.APIResponse;
import com.wixis360.neuroco_backend.dto.ErrorDto;
import com.wixis360.neuroco_backend.enums.APIResponseStatus;
import com.wixis360.neuroco_backend.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public APIResponse<?> handleNotFoundException(NotFoundException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus(APIResponseStatus.ERROR.name());
        serviceResponse.setErrors(Collections.singletonList(new ErrorDto("", exception.getMessage())));
        return serviceResponse;
    }
}
