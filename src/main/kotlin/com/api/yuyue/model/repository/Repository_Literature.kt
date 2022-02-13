package com.api.yuyue.model.repository;

import com.api.yuyue.model.entity.EntityLiterature
import com.api.yuyue.model.entity.EntityLiteraturePreview
import com.api.yuyue.model.entity.EntitySeries
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface RepositorySeries : JpaRepository<EntitySeries, String> {

    @Query(value = "select s from Entity_Series s where s.topic = ?1")
    fun findBySeries(series: String): EntitySeries?
}

interface RepositoryLiterature : JpaRepository<EntityLiterature, Int> {

    @Query(value = "select el from Entity_Literature el where el.title = ?1 and el.publish = true")
    fun findByTitle(title : String) : EntityLiterature?

    @Query(value = "select el from Entity_Literature el where el.id = ?1 and el.publish = true")
    fun findPreviewBySeries(id : Int) : List<EntityLiteraturePreview>

    @Query(value = "select el from Entity_Literature el where el.publish = true")
    fun findAllPreviews() : List<EntityLiteraturePreview>

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "update Entity_Literature el set el.publish = true where el.id = ?1")
    fun publish(id: Int)
}