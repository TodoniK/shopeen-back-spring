package com.orange.shopeenback

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {
    @Bean
    fun swaggerOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Shopeen API")
                    .description("Back-end API of shopeen app\n Manage all user responses and also calculate final power, CO2 and money consumption.")
            )

    }
}