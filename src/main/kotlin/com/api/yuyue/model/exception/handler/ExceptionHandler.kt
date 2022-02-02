package com.api.yuyue.model.exception.handler

import com.api.yuyue.model.exception.NotFoundException
import com.api.yuyue.model.exception.ParameterInvalidException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun notFoundHandler(e : NotFoundException) : ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ParameterInvalidException::class)
    fun paramInvalidHandler(e : ParameterInvalidException) : ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NO_CONTENT)
    }
}