package com.api.yuyue.model.repository;

import com.api.yuyue.model.entity.EntityLiterature
import com.api.yuyue.model.entity.EntityLiteraturePreview
import com.api.yuyue.model.entity.EntitySeries
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface RepositorySeries : JpaRepository<EntitySeries, String> {

    @Query(value = "select s from EntitySeries s where s.id = ?1")
    fun findById(id: Int): EntitySeries?

    @Query(value = "select s from EntitySeries s where s.topic = ?1")
    fun findBySeries(series: String): EntitySeries?

    @Query(value = "select s from EntitySeries s order by s.amount desc")
    fun findTopSeries(page: Pageable): List<EntitySeries>
}

interface RepositoryLiterature : JpaRepository<EntityLiterature, Int> {

    @Query(value = "select el from EntityLiterature el where el.title = ?1 and el.publish = true")
    fun findByTitle(title : String) : EntityLiterature?

    @Query(value = "select el from EntityLiterature el where el.series = ?1 and el.publish = true order by el.id desc")
    fun findPreviewBySeries(seriesName : String) : List<EntityLiteraturePreview>

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "update EntityLiterature el set el.publish = true where el.id = ?1")
    fun publish(id: Int)

    @Query(value = "select el from EntityLiterature el order by el.updateOn desc")
    fun findAllPreviewsIncludeUnpublished(): List<EntityLiteraturePreview>
}