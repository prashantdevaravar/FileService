package com.fileservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileservice.exception.InputDataValidationException;
import com.fileservice.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = { "*" }, maxAge = 4800, allowCredentials = "false")
@SwaggerDefinition(tags = { @Tag(name = Constants.SWAGGER_API_TITLE, description = Constants.SWAGGER_API_DESCRIPTION) })
@Api(tags = { Constants.SWAGGER_API_TITLE })
public class FileOperation {
	
	private static final Logger LOG = LogManager.getLogger(FileOperation.class);

	@PostMapping(path = "/create", produces = "application/json")
	@ApiOperation(Constants.SWAGGER_FILE_CREATE_API_RETURN_DESCRIPTION)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = String.class) })
	public String createFile(@RequestParam("file") MultipartFile file) {
		if(file == null) {
			String errorMessage = Constants.FILE_NOT_VALID_ERROR_MESSAGE;
			LOG.error(errorMessage);
			throw new InputDataValidationException(errorMessage);
		}
		
		JSONObject response = new JSONObject();
		/**
		 * Business Logic
		 */
		String message = Constants.FILE_KEY + " " + file + " copied Successfully.";
		LOG.info(message);
		response.put(Constants.STATUS_KEY + " : ", message);
		return response.toString();
	}
	
	@GetMapping(path = "/download", produces = "application/json")
	@ApiOperation(Constants.SWAGGER_FILE_DOWNLIAD_API_RETURN_DESCRIPTION)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = String.class) })
	public String downloadFile(@RequestParam("fileName") String file) {
		if(file == null || file.isEmpty()) {
			String errorMessage = Constants.FILE_NOT_VALID_ERROR_MESSAGE;
			LOG.error(errorMessage);
			throw new InputDataValidationException(errorMessage);
		}
		
		JSONObject response = new JSONObject();
		/**
		 * Business Logic
		 */
		String message = Constants.FILE_KEY + " " + file + " download started Successfully.";
		LOG.info(message);
		response.put(Constants.STATUS_KEY + " : ", message);
		return response.toString();
	}
	
	
	@DeleteMapping(path = "/delete", produces = "application/json")
	@ApiOperation(Constants.SWAGGER_FILE_DELETE_API_RETURN_DESCRIPTION)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = String.class) })
	public String deleteFile(@RequestParam("fileName") String file) {
		if(file == null || file.isEmpty()) {
			String errorMessage = Constants.FILE_NOT_VALID_ERROR_MESSAGE;
			LOG.error(errorMessage);
			throw new InputDataValidationException(errorMessage);
		}
		
		JSONObject response = new JSONObject();
		/**
		 * Business Logic
		 */
		String message = Constants.FILE_KEY + " " + file + " deleted Successfully.";
		LOG.info(message);
		response.put(Constants.STATUS_KEY + " : ", message);
		return response.toString();
	}
}
