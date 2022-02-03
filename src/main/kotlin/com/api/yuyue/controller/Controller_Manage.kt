package com.api.yuyue.controller

import com.api.yuyue.model.entity.Entity_User
import com.api.yuyue.service.Service_User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
@RequestMapping("/manage")
class Controller_Manage {

    @Autowired
    lateinit var userService : Service_User

    @GetMapping("/login")
    fun test() : Any? {
        val user = SecurityContextHolder.getContext().authentication.principal
        val auth = user as? OAuth2User
        val name = auth?.getAttribute<String>("name")
        val email = auth?.getAttribute<String>("email")


        return if(name != null && email != null) {
            userService.tryGetUser(name, email)
        } else {
            null
        }
    }
}