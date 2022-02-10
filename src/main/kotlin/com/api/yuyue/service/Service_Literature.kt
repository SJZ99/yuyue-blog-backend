package com.api.yuyue.service

import com.api.yuyue.model.exception.NotFoundException
import com.api.yuyue.model.entity.Entity_Literature
import com.api.yuyue.model.entity.Entity_Literature_Preview
import com.api.yuyue.model.entity.Entity_Program
import com.api.yuyue.model.entity.Entity_Series
import com.api.yuyue.model.repository.Repository_Literature
import com.api.yuyue.model.repository.Repository_Series
import org.springframework.stereotype.Service

@Service
class Service_Literature (
    private val seriesRepository : Repository_Series,
    private val literatureRepository : Repository_Literature
) {
    companion object {
        private const val notFoundMessage : String = "Literature Article Not Found"
    }

    fun getAllSeries() : List<Entity_Series> {
        return seriesRepository.findAll()
    }

    fun getAllPreviews() : List<Entity_Literature_Preview> {
        return literatureRepository.findAllPreviews()
    }

    fun getPreviews(id : Int) : List<Entity_Literature_Preview>{
        return literatureRepository.findPreviewBySeries(id)
    }

    fun getArticleByTitle(title : String) : Entity_Literature {
        return literatureRepository.findByTitle(title) ?: throw NotFoundException(notFoundMessage)
    }

    fun getArticleById(id : Int) : Entity_Literature {
        return literatureRepository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun saveArticle(article : Entity_Literature) {
        literatureRepository.save(article)
    }

    fun updateArticle(article : Entity_Literature) {
        val entity : Entity_Literature = literatureRepository.getById(article.id)

        entity.content = article.content
        entity.img = article.img
        entity.series = article.series
        entity.preface = article.preface
        entity.title = article.title
        entity.updateOn = article.updateOn

        literatureRepository.save(entity)
    }
}