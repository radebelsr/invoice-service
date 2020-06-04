package za.co.bmw.invoice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class BeanConfig {
    @Bean
    public SwaggerConfig swaggerConfig(){
        return new SwaggerConfig(
                "Customer Invoice Service",
                "BMW API To Customer Invoicing :",
                "1.0.0");
    }
}