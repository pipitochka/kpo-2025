package zoo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class of swagger config.
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "My API", version = "v1", description = "Documentation for my API"))
public class SwaggerConfig {

    /**
     * function to configure swagger.
     *
     * @return OpenAPI with information.
     */
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("My API")
                        .version("v1")
                        .description("API Documentation"));
    }
}
