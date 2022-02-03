package com.api.yuyue.service

import com.api.yuyue.model.entity.Entity_User
import com.api.yuyue.model.repository.Repository_User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class Service_User {

    @Autowired
    lateinit var userRepository : Repository_User

    fun tryGetUser(name : String, email : String) : Entity_User {
        val user = userRepository.findByEmail(email)

        return if (user != null) {
            user
        } else {
            val entity : Entity_User = Entity_User(email = email, name = name)
            userRepository.save(entity)
            entity
        }
    }
}