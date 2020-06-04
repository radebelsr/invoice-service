package za.co.bmw.invoice.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfig extends Docket {
    public SwaggerConfig(String apiName, String apiDescription, String apiVersion) {
        super(DocumentationType.SWAGGER_2);
        this.select().apis(RequestHandlerSelectors.basePackage("za.co.bmw.invoice")).paths(PathSelectors.any()).build().apiInfo(this.apiInfo(apiName, apiDescription, apiVersion));
    }

    private ApiInfo apiInfo(String apiName, String apiDescription, String apiVersion) {
        return (new ApiInfoBuilder())
                .title(apiName)
                .description(apiDescription)
                .contact(new Contact("BMW Development Team", "http://bmw.co.za/development", "developer@bmw.co.za")).license("Terms & Conditions")
                .licenseUrl("http://bmw.co.za/license").version(apiVersion)
                .build();
    }
}
