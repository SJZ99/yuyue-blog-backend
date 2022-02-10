package com.api.yuyue.service

import com.api.yuyue.model.entity.Entity_User
import com.api.yuyue.model.exception.NotFoundException
import com.api.yuyue.model.repository.Repository_User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class Service_User {

    @Autowired
    private lateinit var userRepository : Repository_User

    /**
     * Try use email to find user, if not found will auto save it to repository.
     *
     */
    fun saveUser(_name : String,
                   _email : String,
                   _roles : List<String>
    ) : Entity_User {

        val entity : Entity_User = Entity_User(email = _email, name = _name, roles = _roles)

        userRepository.save(entity)

        return entity
    }
}