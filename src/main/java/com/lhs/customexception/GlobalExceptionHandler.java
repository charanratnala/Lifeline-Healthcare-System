package com.lhs.customexception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	
	
	@ExceptionHandler(ExceptionPayload.class) 
	public ResponseEntity<String> handle(ExceptionPayload error) {

		return new ResponseEntity<String>("already username exists", HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handled(RuntimeException error) {
		return new ResponseEntity<String>("mail doesnt exists", HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(UpdationException.class)
	ResponseEntity<String> handle(UpdationException error) {

		return new ResponseEntity<String>("Some thing went Wrong !", HttpStatus.BAD_REQUEST);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exx,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> map = new HashMap<>();

		exx.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldname = ((FieldError) error).getField();
			String message = error.getDefaultMessage();

			map.put(fieldname, message);
		});
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);

	}

}
