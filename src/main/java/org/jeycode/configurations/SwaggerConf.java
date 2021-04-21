package org.jeycode.configurations;

import org.jeycode.utilities.SwaggerStrings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConf implements SwaggerStrings
{

      @Bean
      public Docket api(ApiInfo apiInfo)
      {

            return new Docket(DocumentationType.SWAGGER_2).select()
                                                          .apis(RequestHandlerSelectors.basePackage("org.jeycode"))
                                                          .paths(PathSelectors.any())
                                                          .build()
                                                          .apiInfo(apiInfo);
      }

      @Bean
      public ApiInfo apiInfo()
      {

            return new ApiInfoBuilder().title(API_REST_TITLE)
                                       .description(API_DESCRIPTION)
                                       .version(API_REST_VERSION)
                                       .contact(new Contact(AUTHOR,AUTHOR_URL_YT,AUTHOR_MAIL))
                                       .license(LICENSE_NAME)
                                       .licenseUrl(LICENSE_URL)
                                       .termsOfServiceUrl(LICENSE_TERMS)
                                       .build();
      }

}
