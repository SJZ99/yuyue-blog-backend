package com.api.yuyue.model.repository

import com.api.yuyue.model.entity.EntityUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RepositoryUser : JpaRepository<EntityUser, Int> {

    @Query(value = "select user from EntityUser user where user.email = ?1")
    fun findByEmail(email : String) : EntityUser?

}