package com.api.yuyue.controller

import com.api.yuyue.model.entity.Entity_Literature
import com.api.yuyue.model.entity.Entity_Program
import com.api.yuyue.model.exception.ParameterInvalidException
import com.api.yuyue.service.Service_Image
import com.api.yuyue.service.Service_Literature
import com.api.yuyue.service.Service_Program
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/writer")
class Controller_Writer(
    val programService : Service_Program,
    val literatureService : Service_Literature,
    val imgService : Service_Image,
) {

    @PostMapping("/program/add-article")
    fun addArticle(
        @RequestBody article: Entity_Program
    ) {
        programService.saveArticle(article)
    }

    @PostMapping("/program/add-img")
    fun addProgramImg(@RequestPart img : MultipartFile) : String{
        val saveActionResult = imgService.saveImg("program", img)
        saveActionResult ?: throw ParameterInvalidException("The uploaded img is empty")
        return saveActionResult
    }

    @PostMapping("/literature/add-article")
    fun addArticle(
        @RequestBody article : Entity_Literature
    ) {
        literatureService.saveArticle(article)
    }

    @PostMapping("/literature/add-img")
    fun addLiteratureImg(@RequestParam img : MultipartFile): String {
        val saveActionResult = imgService.saveImg("literature", img)
        saveActionResult ?: throw ParameterInvalidException("The uploaded img is empty")
        return saveActionResult
    }

}