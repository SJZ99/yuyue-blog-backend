package com.api.yuyue.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller_Error : ErrorController{

    @RequestMapping("/error")
    fun error() : ResponseEntity<String> {
        return ResponseEntity("ERROR", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}