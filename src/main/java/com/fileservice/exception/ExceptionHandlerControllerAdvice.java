package com.fileservice.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fileservice.utils.Constants;

@ControllerAdvice(basePackages = {"com.fileservice.controller"})
public class ExceptionHandlerControllerAdvice {
	
	private static final Logger LOG = LogManager.getLogger(ExceptionHandlerControllerAdvice.class);

	@ExceptionHandler(InputDataValidationException.class)
	public ResponseEntity<Object> inputConfigurationFileLoaderException(InputDataValidationException e) {
		String errorMessage = "Input Data Arguments Validation Failed, Please Verfiy";
        LOG.error(Constants.ERROR_MESSAGE_SNIPPET, errorMessage, e.getMessage(), e);
        ExceptionHandlerResponse error = new ExceptionHandlerResponse(errorMessage);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
