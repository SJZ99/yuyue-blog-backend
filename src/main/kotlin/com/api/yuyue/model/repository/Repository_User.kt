package com.api.yuyue.model.repository

import com.api.yuyue.model.entity.Entity_User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface Repository_User : JpaRepository<Entity_User, Int> {

    @Query(value = "select user from Entity_User user where user.email = ?1")
    fun findByEmail(email : String) : Entity_User?

}