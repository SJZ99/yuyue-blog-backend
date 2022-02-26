package com.api.yuyue.service

import com.api.yuyue.model.dto.DtoNewArticle
import com.api.yuyue.model.dto.DtoUpdateArticle
import com.api.yuyue.model.entity.EntityLanguage
import com.api.yuyue.model.entity.EntityProgram
import com.api.yuyue.model.entity.EntityProgramPreview
import com.api.yuyue.model.entity.EntityProgramTags
import com.api.yuyue.model.exception.NotFoundException
import com.api.yuyue.model.exception.ParameterInvalidException
import com.api.yuyue.model.repository.RepositoryLanguage
import com.api.yuyue.model.repository.RepositoryProgram
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collector
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
class ServiceProgram(
    private val languageRepository : RepositoryLanguage,
    private val programRepository : RepositoryProgram
) {

    companion object {
        private const val notFoundMessage : String = "Programming Article Not Found"
    }

    data class PartialEntity(
        val title: String,
        val preface: String,
        val content: String,
        val tags: Set<String>
    )

    private fun analysisDom(html: String): PartialEntity {

        val doc: Document = Jsoup.parse(html)
        val title = doc.getElementsByTag("h1").first()?.text()
            ?: doc.getElementsByTag("h2").first()?.text()
            ?: throw ParameterInvalidException("Article's title not found, check the article has <h1> or <h2>")

        val tags: Set<String> =
            if (title.contains("【") && title.contains("】")) {
                title
                    .split("】")[0]
                    .replace("【", "", true)
                    .split(" ")
                    .stream()
                    .collect(Collectors.toSet())
            } else {
                setOf()
            }

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
            tags = tags,
        )
    }

    fun dtoToEntity(dto: DtoNewArticle): EntityProgram {
        val (fullContent, subtype, imgName) = dto
        val (title, preface, content, tags) = analysisDom(fullContent)

        return EntityProgram(
            lang = subtype,
            title = title,
            preface = preface,
            content = content,
            tags = tags,
            img = imgName
        )
    }

    fun dtoToEntity(dto: DtoUpdateArticle): EntityProgram? {
        val ( id: Int, fullContent: String?, img: String?) = dto
        var partialEntity: PartialEntity? = null

        if(fullContent == null && img == null) return null

        if(fullContent != null) partialEntity = analysisDom(fullContent)

        return EntityProgram(
            id = id,
            lang = "",
            title = partialEntity?.title ?: "",
            content = partialEntity?.content ?: "",
            preface = partialEntity?.preface ?: "",
            tags = partialEntity?.tags ?: setOf(),
            img = img
        )
    }

    fun saveArticle(article : EntityProgram) {
        programRepository.save(article)

        val language = article.lang
        val langEntity = languageRepository.findByLanguage(language)

        if(langEntity == null) {
            languageRepository.save(EntityLanguage(language))
        }
    }

    fun updateArticle(article : EntityProgram) {
        article.id?.let {
            val entity : EntityProgram = programRepository.findById(it).get()

            if(article.content.isNotEmpty())                      entity.content = article.content

            if(article.img?.isNotEmpty() == true)                 entity.img = article.img

            if(article.tags.isNotEmpty())                         entity.tags = article.tags

            if(article.preface.isNotEmpty())                      entity.preface = article.preface

            if(article.title.isNotEmpty())                        entity.title = article.title

            programRepository.save(entity)
        }
    }

    @Transactional
    fun publish(id: Int) {
        val entity = programRepository.findById(id)

        if(entity.isPresent) {
            programRepository.publish(id)
        }
    }

    fun getAllLanguages() : List<EntityLanguage> {
        return languageRepository.findAll()
    }

    fun getPreviews(lang : String) : List<EntityProgramPreview> {
        return programRepository.findPreviewByLanguage(lang)
    }

    fun getArticleByTitle(title : String) : EntityProgram {
        return programRepository.findByTitle(title) ?: throw NotFoundException(notFoundMessage)
    }

    fun getArticleById(id : Int) : EntityProgram {
        return programRepository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun getAllPreviewsIncludeUnpublished(): List<EntityProgramPreview> {
        return programRepository.findAllPreviewsIncludeUnpublished()
    }

    fun getAllTags(): Set<String> {
        val entitySet = programRepository.findAllTags()
        val tags = mutableSetOf<String>()
        entitySet.forEach {
            entity ->
            tags.addAll(entity.tags)
        }
        return tags
    }

    fun getTop2Previews(): List<EntityProgramPreview> {
        return programRepository.findTopPreviews(PageRequest.of(0, 2))
    }
}

