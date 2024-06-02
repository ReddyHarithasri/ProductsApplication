package Springboot.Api.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus)
    {
        Map<String ,Object> response= new HashMap<>();
        response.put("message",message);
        response.put("httpStatus",httpStatus);
//        response.put("date",LocalDateTime.now());

        return new ResponseEntity<Object>(response,httpStatus);
    }
}
