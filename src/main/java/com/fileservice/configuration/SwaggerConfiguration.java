package com.fileservice.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fileservice.controller.FileOperation;
import com.fileservice.utils.Constants;
import com.google.common.base.Predicate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = FileOperation.class)
public class SwaggerConfiguration {

	@Bean
	public Docket fileOperationApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName(Constants.SWAGGER_API_GROUP_NAME).apiInfo(getApiInfo())
				.select().apis(RequestHandlerSelectors.basePackage(Constants.SWAGGER_API_BASE_PACKAGE)).paths(paths())
				.build().pathMapping("/");
	}

	@SuppressWarnings("rawtypes")
	protected ApiInfo getApiInfo() {
		return new ApiInfo(Constants.SWAGGER_API_TITLE, Constants.SWAGGER_API_DESCRIPTION, "1.0", "urn:tos",
				new Contact(Constants.SWAGGER_API_CONTACT_NAME, Constants.SWAGGER_API_CONTACT_URL,
						Constants.SWAGGER_API_CONTACT_EMAIL),
				"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html", new ArrayList<VendorExtension>());
	}

	protected Predicate<String> paths() {
		return PathSelectors.any();
	}
}
