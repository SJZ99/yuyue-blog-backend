package com.api.yuyue.model.repository

import com.api.yuyue.model.entity.EntityLanguage
import com.api.yuyue.model.entity.EntityProgram
import com.api.yuyue.model.entity.EntityProgramPreview
import com.api.yuyue.model.entity.EntityProgramTags
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface RepositoryLanguage : JpaRepository<EntityLanguage, String> {

    @Query(value = "select l from EntityLanguage l where l.language = ?1")
    fun findByLanguage(language: String): EntityLanguage?
}

interface RepositoryProgram : JpaRepository<EntityProgram, Int> {

    @Query(value = "select ep from EntityProgram ep")
    fun findAllTags(): Set<EntityProgramTags>

    @Query(value = "select ep from EntityProgram ep where ep.title = ?1 and ep.publish = true")
    fun findByTitle(title : String) : EntityProgram?

    @Query(value = "select ep from EntityProgram ep where ep.lang = ?1 and ep.publish = true order by ep.id desc")
    fun findPreviewByLanguage(lang : String) : List<EntityProgramPreview>

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "update EntityProgram ep set ep.publish = true where ep.id = ?1")
    fun publish(id: Int)

    @Query(value = "select ep from EntityProgram ep order by ep.updateOn desc")
    fun findAllPreviewsIncludeUnpublished(): List<EntityProgramPreview>

    @Query(value = "select ep from EntityProgram ep order by ep.id desc")
    fun findTopPreviews(page: Pageable): List<EntityProgramPreview>
}