package com.zensar.om.mns.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalException extends RuntimeException {

	public GlobalException(String exception) {
		super(exception);
		//System.out.println("aa");
	}
}
