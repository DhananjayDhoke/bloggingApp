package com.bloggingapp.demo.exception;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustmizeErrorDetails> myResourceNotFoundException(Exception e,WebRequest req) {
		CustmizeErrorDetails err=new CustmizeErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
	return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
		    String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();	
			resp.put(fieldName, message);
		});
		
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
      
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustmizeErrorDetails> myExceptionHandler(Exception e,WebRequest req) {
		CustmizeErrorDetails err=new CustmizeErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
	return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
}
