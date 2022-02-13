package com.api.yuyue.controller

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/manage")
class ControllerManage {

    @GetMapping("/test")
    fun test() : Any {
        val con: SecurityContext = SecurityContextHolder.getContext()
        val a = con.authentication.authorities
        return a.map { auth -> auth.authority }
    }
}