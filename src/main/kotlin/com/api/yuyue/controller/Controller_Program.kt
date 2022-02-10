package com.api.yuyue.controller

import com.api.yuyue.model.entity.Entity_Language
import com.api.yuyue.model.entity.Entity_Program
import com.api.yuyue.model.entity.Entity_Program_Preview
import com.api.yuyue.model.exception.ParameterInvalidException
import com.api.yuyue.service.Service_Image
import com.api.yuyue.service.Service_Program
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/program")
class Controller_Program(val programService : Service_Program,
                         val imgService : Service_Image
                         ) {

    @GetMapping("/languages")
    fun getLanguage() : List<Entity_Language> {
        return programService.getAllLanguages()
    }

    @GetMapping("/previews")
    fun getAllPreviews() : List<Entity_Program_Preview> {
        return programService.getAllPreviews()
    }

    @GetMapping("/previews/{lang}")
    fun getPreview(
        @PathVariable("lang") lang : String
    ) : List<Entity_Program_Preview> {
        return programService.getPreviews(lang)
    }

    @GetMapping("/articles/{id}")
    fun getArticle(
        @PathVariable("id") id : Int
    ) : Entity_Program {
        return programService.getArticleById(id)
    }

    @GetMapping("/img/{fileName}")
    fun getImg(@PathVariable("fileName") fileName : String) : ByteArray {
        return imgService.getImg("program", fileName)
    }
}