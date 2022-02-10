package com.api.yuyue.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@RestController
class Controller_Error : ErrorController{

    @RequestMapping("/error")
    fun error(request : HttpServletRequest) : ResponseEntity<String> {
        return ResponseEntity("${request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)} ${request.getAttribute(RequestDispatcher.ERROR_MESSAGE)}",
            HttpStatus.valueOf(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) as Int))
    }
}