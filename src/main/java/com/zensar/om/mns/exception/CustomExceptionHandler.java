package com.zensar.om.mns.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
		//System.out.println("bbb");
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Record Not Found", details);
		//System.out.println("ccc");
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(GlobalException.class)
	public final ResponseEntity<Object> badRequestException(GlobalException ex, WebRequest request) {
		//System.out.println("bbb");
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Global Exeception.", details);
		//System.out.println("ccc");
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DateFormatInvalidException.class)
	public final ResponseEntity<Object> dateFormatInvalid(DateFormatInvalidException ex, WebRequest request) {
	//System.out.println("bbb");
	List<String> details = new ArrayList<>();
	details.add(ex.getLocalizedMessage());
	ErrorResponse error = new ErrorResponse("Global Exeception.", details);
	//System.out.println("ccc");
	return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ExcelSequenceInvalid.class)
	public final ResponseEntity<Object> excelSequenceInvalid(ExcelSequenceInvalid ex, WebRequest request) {
	//System.out.println("bbb");
	List<String> details = new ArrayList<>();
	details.add(ex.getLocalizedMessage());
	ErrorResponse error = new ErrorResponse("Incorrect Sequence", details);
	//System.out.println("ccc");
	return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}

}
