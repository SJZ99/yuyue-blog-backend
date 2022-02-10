package com.api.yuyue.model.repository;

import com.api.yuyue.model.entity.Entity_Literature
import com.api.yuyue.model.entity.Entity_Literature_Preview
import com.api.yuyue.model.entity.Entity_Series
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface Repository_Series : JpaRepository<Entity_Series, String>;

interface Repository_Literature : JpaRepository<Entity_Literature, Int> {

    @Query(value = "select el from Entity_Literature el where el.title = ?1")
    fun findByTitle(title : String) : Entity_Literature?

    @Query(value = "select el from Entity_Literature el where el.id = ?1")
    fun findPreviewBySeries(id : Int) : List<Entity_Literature_Preview>

    @Query(value = "select el from Entity_Literature el")
    fun findAllPreviews() : List<Entity_Literature_Preview>
}