package com.api.yuyue.controller

import com.api.yuyue.model.entity.Entity_User
import com.api.yuyue.service.Service_User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.security.Principal


@RestController
@RequestMapping("/manage")
class Controller_Manage {

    @GetMapping("/test")
    fun test() : Any {
        val con: SecurityContext = SecurityContextHolder.getContext()
        val a = con.authentication.authorities
        return a.map { auth -> auth.authority }
    }
}