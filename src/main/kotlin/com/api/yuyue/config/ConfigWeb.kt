package com.api.yuyue.config

//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8080/", "http://localhost:8180/")
            .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
            .maxAge(3600)
            .allowedHeaders("*")
            .allowCredentials(true)
            .exposedHeaders("Authorization")
    }

    @Bean(value = ["jwtDecoder"])
    fun getJwtDecode() : JwtDecoder {
        return JwtDecoders.fromIssuerLocation<JwtDecoder>("http://localhost:8180/auth/realms/yuyue-blog")
    }

}