package org.jeycode.configurations;

import org.jeycode.utilities.SwaggerStrings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SwaggerDefinition(info = @Info(description = "Buenas Coders, esta API Rest conforma el Backend para las aplicaciones de desktop, mobile o web realizadas por Jey Code para la gestión"
                        + " de porras legales y no lucrativas de grupos o peñas asociadas al Burgos C.F, sobre el cual gira la aplicación.", contact = @Contact(name = "Javier Pérez Alonso", email = "j.code.initialize@gmail.com", url = "https://www.youtube.com/channel/UC5xqFtQ9o3gNIGABG3s3Yyw"), title = "Burgos C.F. Full Rest Api", version = "1.0"))
public class SwaggerConf implements SwaggerStrings
{

      @Bean
      public Docket api()
      {

            return new Docket(DocumentationType.SWAGGER_2).select()
                                                          .apis(RequestHandlerSelectors.basePackage("org.manga.rest.controllers"))
                                                          .paths(PathSelectors.any())
                                                          .build()
                                                          .apiInfo(apiInfo());
      }

      @Bean
      public ApiInfo apiInfo()
      {

            return new ApiInfoBuilder().description("API Rest ....")
                                       .build();

      }
}
