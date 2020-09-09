package com.fileservice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fileservice.exception.FileStorageException;
import com.fileservice.exception.InputDataValidationException;
import com.fileservice.payload.UploadFileResponse;
import com.fileservice.service.FileStorageService;
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

	@Autowired
    private FileStorageService fileStorageService;
	private static final Logger LOG = LogManager.getLogger(FileOperation.class);

	@PostMapping(path = "/create", produces = "application/json")
	@ApiOperation(Constants.SWAGGER_FILE_CREATE_API_RETURN_DESCRIPTION)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = String.class) })
	public UploadFileResponse createFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);

		
		if(file == null) {
			String errorMessage = Constants.FILE_NOT_VALID_ERROR_MESSAGE;
			LOG.error(errorMessage);
			throw new InputDataValidationException(errorMessage);
		}
		
		/**
		 * Business Logic
		 */
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
		String message = Constants.FILE_KEY + " " + file + " copied Successfully.";
		LOG.info(message);
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
		
		
	}
	
	@GetMapping("/download/{fileName}")
	@ApiOperation(Constants.SWAGGER_FILE_DOWNLIAD_API_RETURN_DESCRIPTION)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = String.class) })
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		if(fileName == null || fileName.isEmpty()) {
			String errorMessage = Constants.FILE_NOT_VALID_ERROR_MESSAGE;
			LOG.error(errorMessage);
			throw new InputDataValidationException(errorMessage);
		}
		
		/**
		 * Business Logic
		 */
		// Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            LOG.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        String message = Constants.FILE_KEY + " " + fileName + " download started Successfully.";
		LOG.info(message);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
		
	}
	
	
	@DeleteMapping(path = "delete/{fileName}", produces = "application/json")
	@ApiOperation(Constants.SWAGGER_FILE_DELETE_API_RETURN_DESCRIPTION)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = String.class) })
	public String deleteFile(@PathVariable String fileName) {
		if(fileName == null || fileName.isEmpty()) {
			String errorMessage = Constants.FILE_NOT_VALID_ERROR_MESSAGE;
			LOG.error(errorMessage);
			throw new InputDataValidationException(errorMessage);
		}
		
		JSONObject response = new JSONObject();
		/*
		 * Business Logic
		 */
	
		String message;
		boolean result = fileStorageService.deleteFile(fileName);
			//result = Files.deleteIfExists(Paths.get(fileName));
			
	        if (result) {
	        	message=Constants.FILE_KEY + " " + fileName + " deleted Successfully.";
	        } else {
	        	message=Constants.FILE_KEY+ " " + fileName + " Could not delete.";
	        }
	        LOG.info(message);
			response.put(Constants.STATUS_KEY + " : ", message);
			return response.toString();
			
	}
}
