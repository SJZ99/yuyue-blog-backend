package com.api.yuyue.controller

import com.api.yuyue.model.dto.DtoNewArticle
import com.api.yuyue.model.dto.DtoUpdateArticle
import com.api.yuyue.model.entity.EntityLiteraturePreview
import com.api.yuyue.model.entity.EntityProgramPreview
import com.api.yuyue.model.exception.ParameterInvalidException
import com.api.yuyue.service.ServiceImage
import com.api.yuyue.service.ServiceLiterature
import com.api.yuyue.service.ServiceProgram
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/writer")
class ControllerWriter(
    val programService : ServiceProgram,
    val literatureService : ServiceLiterature,
    val imgService : ServiceImage,
) {

    @GetMapping("/program/previews")
    fun getProgramPreviews(): List<EntityProgramPreview> {
        return programService.getAllPreviewsIncludeUnpublished()
    }

    @GetMapping("/literature/previews")
    fun getLiteraturePreviews(): List<EntityLiteraturePreview> {
        return literatureService.getAllPreviewsIncludeUnpublished()
    }

    @PutMapping("/program/publish/{id}")
    fun publishProgram(
        @PathVariable(value = "id", required = true) id: Int
    ) {
        programService.publish(id)
    }

    @PutMapping("/program/update-article")
    fun updateProgramArticle(
        @RequestBody dto: DtoUpdateArticle
    ) {
        val entity = programService.dtoToEntity(dto)
        println("${entity?.img} ${entity?.tags} ${entity?.id}")
        if(entity != null) {
            programService.updateArticle(entity)
        }
    }

    @PostMapping("/program/add-article")
    fun addProgramArticle(
        @RequestBody dto: DtoNewArticle
    ) {
        val entity = programService.dtoToEntity(dto)
        programService.saveArticle(entity)
    }

    @PostMapping("/program/add-img")
    fun addProgramImg(@RequestPart(value = "img") img : MultipartFile) : String{
        val saveActionResult = imgService.saveImg("program", img)
        saveActionResult ?: throw ParameterInvalidException("The uploaded img is empty")
        return saveActionResult
    }

    @PutMapping("/literature/publish/{id}")
    fun publishLiterature(
        @PathVariable(value = "id", required = true) id: Int
    ) {
        literatureService.publish(id)
    }

    @PutMapping("/literature/update-article")
    fun updateLiteratureArticle(
        @RequestBody dto: DtoUpdateArticle
    ) {
        val entity = literatureService.dtoToEntity(dto)
        if(entity != null) {
            literatureService.updateArticle(entity)
        }
    }

    @PostMapping("/literature/add-article")
    fun addLiteratureArticle(
        @RequestBody dto : DtoNewArticle
    ) {
        val entity = literatureService.dtoToEntity(dto)
        literatureService.saveArticle(entity)
    }

    @PostMapping("/literature/add-img")
    fun addLiteratureImg(@RequestParam img : MultipartFile): String {
        val saveActionResult = imgService.saveImg("literature", img)
        saveActionResult ?: throw ParameterInvalidException("The uploaded img is empty")
        return saveActionResult
    }

}