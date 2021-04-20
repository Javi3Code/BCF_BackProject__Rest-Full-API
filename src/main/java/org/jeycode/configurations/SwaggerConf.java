package org.jeycode.configurations;

import org.jeycode.utilities.SwaggerStrings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SwaggerDefinition(info = @Info(title = SwaggerStrings.API_REST_TITLE, version = SwaggerStrings.API_REST_VERSION,
      description = SwaggerStrings.API_DESCRIPTION,
      contact = @Contact(name = SwaggerStrings.AUTHOR, email = SwaggerStrings.AUTHOR_MAIL, url = SwaggerStrings.AUTHOR_URL_YT),
      license = @License(name = SwaggerStrings.LICENSE_NAME, url = SwaggerStrings.LICENSE_URL),
      termsOfService = SwaggerStrings.LICENSE_TERMS), host = "HostName")
@EnableSwagger2
public class SwaggerConf
{

      @Bean
      public Docket api()
      {

            return new Docket(DocumentationType.SWAGGER_2).select()
                                                          .apis(RequestHandlerSelectors.basePackage("org.jeycode"))
                                                          .paths(PathSelectors.any())
                                                          .build();
      }

}
