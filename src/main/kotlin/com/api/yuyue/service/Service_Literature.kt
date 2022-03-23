package com.api.yuyue.service

import com.api.yuyue.model.dto.DtoNewArticle
import com.api.yuyue.model.dto.DtoUpdateArticle
import com.api.yuyue.model.entity.EntityLiterature
import com.api.yuyue.model.entity.EntityLiteraturePreview
import com.api.yuyue.model.entity.EntitySeries
import com.api.yuyue.model.exception.NotFoundException
import com.api.yuyue.model.exception.ParameterInvalidException
import com.api.yuyue.model.repository.RepositoryLiterature
import com.api.yuyue.model.repository.RepositorySeries
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ServiceLiterature (
    private val seriesRepository : RepositorySeries,
    private val literatureRepository : RepositoryLiterature
) {
    companion object {
        private const val notFoundMessage : String = "Literature Article Not Found"
    }

    data class PartialEntity(
        val title: String,
        val preface: String,
        val content: String,
    )

    private fun analysisDom(html: String): PartialEntity {

        val doc: Document = Jsoup.parse(html)
        val title = doc.getElementsByTag("h1").first()?.text()
            ?: doc.getElementsByTag("h2").first()?.text()
            ?: throw ParameterInvalidException("Article's title not found, check the article has <h1> or <h2>")

        val prefaceRegex = Regex("<preface>.*</preface>")
        var preface = prefaceRegex.find(html)?.value ?: ""

        preface = preface
            .replace("<preface>", "", true)
            .replace("</preface>", "", true)

        val content = html
            .replace("<h1>$title</h1>", "", true)
            .replace("<h2>$title</h2>", "", true)
            .replace("<preface>$preface</preface>", "", true)

        return PartialEntity(
            title = title,
            preface = preface,
            content = content,
        )
    }

    fun dtoToEntity(dto: DtoNewArticle): EntityLiterature {
        val (fullContent, subtype, imgName) = dto
        val (title, preface, content) = analysisDom(fullContent)

        return EntityLiterature(
            series = subtype,
            title = title,
            preface = preface,
            content = content,
            img = imgName
        )
    }

    fun dtoToEntity(dto: DtoUpdateArticle): EntityLiterature? {
        val ( id: Int, fullContent: String?, img: String?) = dto
        var partialEntity: ServiceLiterature.PartialEntity? = null

        if(fullContent == null && img == null) return null

        if(fullContent != null) partialEntity = analysisDom(fullContent)

        return EntityLiterature(
            id = id,
            series = "",
            title = partialEntity?.title ?: "",
            content = partialEntity?.content ?: "",
            preface = partialEntity?.preface ?: "",
            img = img
        )
    }

    @Transactional
    fun publish(id: Int) {
        val entity = literatureRepository.findById(id)
        if(entity.isPresent) {
            literatureRepository.publish(id)
        }
    }

    fun getAllSeries() : List<EntitySeries> {
        return seriesRepository.findAll()
    }

    fun getPreviews(seriesId : Int) : List<EntityLiteraturePreview>{
        val series = seriesRepository.findById(seriesId) ?: throw NotFoundException("Series not found")
        return literatureRepository.findPreviewBySeries(series.topic)
    }

    fun getArticleByTitle(title : String) : EntityLiterature {
        return literatureRepository.findByTitle(title) ?: throw NotFoundException(notFoundMessage)
    }

    fun getArticleById(id : Int) : EntityLiterature {
        return literatureRepository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun saveArticle(article : EntityLiterature) {
        literatureRepository.save(article)

        val series = article.series
        val seriesEntity = seriesRepository.findBySeries(series)
        if(seriesEntity == null) {
            seriesRepository.save(EntitySeries(topic = series, amount = 1))
        }else {
            seriesEntity.amount += 1
            seriesRepository.save(seriesEntity)
        }
    }

    /**
     * Must provide id
     */
    fun updateArticle(article : EntityLiterature) {
        article.id?.let {
            val entity : EntityLiterature = literatureRepository.getById(it)

            if(article.content.isNotEmpty()) entity.content = article.content
            if(article.img?.isNotEmpty() == true) entity.img = article.img
            if(article.series.isNotEmpty()) entity.series = article.series
            if(article.preface.isNotEmpty()) entity.preface = article.preface
            if(article.title.isNotEmpty()) entity.title = article.title

            literatureRepository.save(entity)
        }
    }

    fun getAllPreviewsIncludeUnpublished(): List<EntityLiteraturePreview> {
        return literatureRepository.findAllPreviewsIncludeUnpublished()
    }

    fun getTop3Series(): List<EntitySeries> {
        return seriesRepository.findTopSeries(PageRequest.of(0, 3))
    }

    fun saveSeries(series: EntitySeries) {
        seriesRepository.save(series)
    }
}

