package com.api.yuyue.controller

import com.api.yuyue.model.entity.EntityLiterature
import com.api.yuyue.model.entity.EntityLiteraturePreview
import com.api.yuyue.model.entity.EntitySeries
import com.api.yuyue.service.ServiceImage
import com.api.yuyue.service.ServiceLiterature
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/literature")
class ControllerLiterature(val literatureService : ServiceLiterature,
                           val imgService : ServiceImage
                            ) {

    @GetMapping("/series")
    fun getAllSeries() : List<EntitySeries> {
        return literatureService.getAllSeries()
    }

    @GetMapping("/series/top3")
    fun getTop3Series(): List<EntitySeries> {
        return literatureService.getTop3Series()
    }

    @GetMapping("/previews/{seriesId}")
    fun getPreview(
        @PathVariable("seriesId") seriesId : Int
    ) : List<EntityLiteraturePreview> {
       return literatureService.getPreviews(seriesId)
    }

    @GetMapping("/articles/{id}")
    fun getArticle(
        @PathVariable("id") id : Int
    ) : EntityLiterature {
        return literatureService.getArticleById(id)
    }

    @GetMapping("/img/{fileName}")
    fun getImg(@PathVariable("fileName") fileName : String) : ByteArray {
        return imgService.getImg("literature", fileName)
    }

}