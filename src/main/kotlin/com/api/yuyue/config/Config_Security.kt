package com.api.yuyue.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class Config_Security : WebSecurityConfigurerAdapter() {

//    override fun configure(web: WebSecurity) {
//        web.ignoring().antMatchers("/**")
//    }

    override fun configure(http: HttpSecurity) {
        //The more specific rules need to come first, followed by the more general ones.
        http.cors()
            .and()
            .csrf().disable()
            .sessionManagement().enableSessionUrlRewriting(false)
            .and()
            .authorizeRequests()
            .antMatchers("/manage/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .oauth2Login()
    }
}