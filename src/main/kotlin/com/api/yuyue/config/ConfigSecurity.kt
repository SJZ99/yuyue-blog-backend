package com.api.yuyue.config

import com.api.yuyue.model.filter.Filter_Jwt
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter


@Configuration
@EnableWebSecurity
class ConfigSecurity(
    val jwtFilter: Filter_Jwt
) : WebSecurityConfigurerAdapter() {

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
            .antMatchers("/writer/**").hasAnyAuthority("writer")
//            .antMatchers("/program/**").hasAnyAuthority("user", "manager")
            .anyRequest().permitAll()
            .and()
            .oauth2ResourceServer()
            .jwt()

        http.addFilterBefore(
            jwtFilter,
            AnonymousAuthenticationFilter::class.java
        )
    }
}