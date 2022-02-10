package com.api.yuyue.model.repository

import com.api.yuyue.model.entity.Entity_Language
import com.api.yuyue.model.entity.Entity_Program
import com.api.yuyue.model.entity.Entity_Program_Preview
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface Repository_Language : JpaRepository<Entity_Language, String> {

    @Query(value = "select l from Entity_Language l where l.language = ?1")
    fun findByLanguage(language: String): Entity_Language?
}

interface Repository_Program : JpaRepository<Entity_Program, Int> {

    @Query(value = "select ep from Entity_Program ep where ep.title = ?1 and ep.publish = true")
    fun findByTitle(title : String) : Entity_Program?

    @Query(value = "select ep from Entity_Program ep where ep.lang = ?1 and ep.publish = true")
    fun findPreviewByLanguage(lang : String) : List<Entity_Program_Preview>

    @Query(value = "select ep from Entity_Program ep where ep.publish = true")
    fun findAllPreviews() : List<Entity_Program_Preview>
}