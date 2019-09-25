package com.zensar.om.mns.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)

public class ExcelSequenceInvalid  extends RuntimeException{
public ExcelSequenceInvalid(String exception) {
super(exception);
//System.out.println("excep occured ");

}

}