package com.api.yuyue.service

import com.api.yuyue.model.exception.NotFoundException
import com.api.yuyue.model.entity.Entity_Language
import com.api.yuyue.model.entity.Entity_Program
import com.api.yuyue.model.entity.Entity_Program_Preview
import com.api.yuyue.model.entity.Entity_Series
import com.api.yuyue.model.repository.Repository_Language
import com.api.yuyue.model.repository.Repository_Program
import org.springframework.stereotype.Service

@Service
class Service_Program(
    private val languageRepository : Repository_Language,
    private val programRepository : Repository_Program
) {

    companion object {
        private const val notFoundMessage : String = "Programming Article Not Found"
    }

    fun saveArticle(article : Entity_Program) {
        programRepository.save(article)

        val language = article.lang
        val langEntity = languageRepository.findByLanguage(language)

        if(langEntity == null) {
            languageRepository.save(Entity_Language(language))
        }
    }

    fun updateArticle(article : Entity_Program) {
        article.id?.let {
            val entity : Entity_Program = programRepository.getById(it)

            entity.content = article.content
            entity.img = article.img
            entity.lang = article.lang
            entity.tags = article.tags
            entity.preface = article.preface
            entity.title = article.title
            entity.updateOn = article.updateOn

            programRepository.save(entity)
        }
    }

    fun getAllLanguages() : List<Entity_Language> {
        return languageRepository.findAll()
    }

    fun getAllPreviews() : List<Entity_Program_Preview> {
        return programRepository.findAllPreviews()
    }

    fun getPreviews(lang : String) : List<Entity_Program_Preview> {
        return programRepository.findPreviewByLanguage(lang)
    }

    fun getArticleByTitle(title : String) : Entity_Program {
        return programRepository.findByTitle(title) ?: throw NotFoundException(notFoundMessage)
    }

    fun getArticleById(id : Int) : Entity_Program {
        return programRepository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
    }
}