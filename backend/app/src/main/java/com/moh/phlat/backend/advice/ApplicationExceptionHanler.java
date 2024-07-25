package com.moh.phlat.backend.advice;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.moh.phlat.backend.exception.HandleInternalException;
import com.moh.phlat.backend.exception.HandleNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHanler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HandleInternalException.class)
    public Map<String, String> handleBusinessException(HandleInternalException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Message", ex.getMessage());
        return errorMap;
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HandleNotFoundException.class)
    public Map<String, String> handleBusinessException(HandleNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Message", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleOtherException(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Message", "The server could not process the request. Please contact customer support");
        return errorMap;
    }
}
