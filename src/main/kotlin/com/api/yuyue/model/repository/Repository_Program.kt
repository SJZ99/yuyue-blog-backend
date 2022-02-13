package com.api.yuyue.model.repository

import com.api.yuyue.model.entity.EntityLanguage
import com.api.yuyue.model.entity.EntityProgram
import com.api.yuyue.model.entity.EntityProgramPreview
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface RepositoryLanguage : JpaRepository<EntityLanguage, String> {

    @Query(value = "select l from Entity_Language l where l.language = ?1")
    fun findByLanguage(language: String): EntityLanguage?
}

interface RepositoryProgram : JpaRepository<EntityProgram, Int> {

    @Query(value = "select ep from Entity_Program ep where ep.title = ?1 and ep.publish = true")
    fun findByTitle(title : String) : EntityProgram?

    @Query(value = "select ep from Entity_Program ep where ep.lang = ?1 and ep.publish = true")
    fun findPreviewByLanguage(lang : String) : List<EntityProgramPreview>

    @Query(value = "select ep from Entity_Program ep where ep.publish = true")
    fun findAllPreviews() : List<EntityProgramPreview>

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "update Entity_Program ep set ep.publish = true where ep.id = ?1")
    fun publish(id: Int)
}