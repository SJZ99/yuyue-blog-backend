package com.api.yuyue.controller

import com.api.yuyue.model.entity.EntityLanguage
import com.api.yuyue.model.entity.EntityProgram
import com.api.yuyue.model.entity.EntityProgramPreview
import com.api.yuyue.service.ServiceImage
import com.api.yuyue.service.ServiceProgram
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/program")
class ControllerProgram(val programService : ServiceProgram,
                        val imgService : ServiceImage
                         ) {

    @GetMapping("/languages")
    fun getLanguage() : List<EntityLanguage> {
        return programService.getAllLanguages()
    }

    @GetMapping("/previews")
    fun getAllPreviews() : List<EntityProgramPreview> {
        return programService.getAllPreviews()
    }

    @GetMapping("/previews/{lang}")
    fun getPreview(
        @PathVariable("lang") lang : String
    ) : List<EntityProgramPreview> {
        return programService.getPreviews(lang)
    }

    @GetMapping("/articles/{id}")
    fun getArticle(
        @PathVariable("id") id : Int
    ) : EntityProgram {
        return programService.getArticleById(id)
    }

    @GetMapping("/img/{fileName}")
    fun getImg(@PathVariable("fileName") fileName : String) : ByteArray {
        return imgService.getImg("program", fileName)
    }
}