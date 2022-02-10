package com.api.yuyue.controller

import com.api.yuyue.model.entity.Entity_Literature
import com.api.yuyue.model.entity.Entity_Literature_Preview
import com.api.yuyue.model.entity.Entity_Series
import com.api.yuyue.model.exception.ParameterInvalidException
import com.api.yuyue.service.Service_Image
import com.api.yuyue.service.Service_Literature
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/literature")
class Controller_Literature(val literatureService : Service_Literature,
                            val imgService : Service_Image
                            ) {

    @GetMapping("/series")
    fun getAllSeries() : List<Entity_Series> {
        return literatureService.getAllSeries()
    }

    @GetMapping("/previews")
    fun getAllPreviews() : List<Entity_Literature_Preview> {
        return literatureService.getAllPreviews()
    }

    @GetMapping("/previews/{seriesId}")
    fun getPreview(
        @PathVariable("seriesId") seriesId : Int
    ) : List<Entity_Literature_Preview> {
       return literatureService.getPreviews(seriesId)
    }

    @GetMapping("/articles/{id}")
    fun getArticle(
        @PathVariable("id") id : Int
    ) : Entity_Literature {
        return literatureService.getArticleById(id)
    }

    @PostMapping("/add_article")
    fun addArticle(
        @RequestBody article : Entity_Literature
    ) {
        literatureService.saveArticle(article)
    }

    @GetMapping("/img/{fileName}")
    fun getImg(@PathVariable("fileName") fileName : String) : ByteArray {
        return imgService.getImg("literature", fileName)
    }

    @PostMapping("/add_img")
    fun addImg(@RequestParam("img") img : MultipartFile) {
        val saveActionResult = imgService.saveImg("literature", img)
        saveActionResult ?: throw ParameterInvalidException("The uploaded img is empty")
    }
}