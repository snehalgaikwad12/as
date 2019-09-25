package com.zensar.om.mns.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)

public class DateFormatInvalidException  extends RuntimeException{
public DateFormatInvalidException(String exception) {
super(exception);
//System.out.println("aa");
}

}
