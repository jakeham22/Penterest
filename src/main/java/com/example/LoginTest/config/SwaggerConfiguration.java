package com.example.LoginTest.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration {

    private final String version = "v1";
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Penterest API")
                .description("Final Project - Penterest API")
                .build();
    }

    /**
     * Docket : Swagger 설정의 핵심이 되는 문서화 객체
     * select(): ApiSelectorBuilder를 통해 객체를 생성합니다.
     * apiInfo :  api 정보
     * apis : api를 탐색할 위치를 설정
     * paths : path 조건에 해당하는 API를 찾아서 문서화
     */
    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(version)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                // swagger에서 error handler 방지 코드
                .paths(PathSelectors.any())
                .build();
    }

}
