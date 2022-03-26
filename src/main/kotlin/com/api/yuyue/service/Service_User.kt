package com.api.yuyue.service

import com.api.yuyue.model.entity.EntityUser
import com.api.yuyue.model.repository.RepositoryUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceUser {

    @Autowired
    private lateinit var userRepository : RepositoryUser

    /**
     * Try use email to find user, if not found will auto save it to repository.
     *
     */
    fun saveUser(_name: String,
                   _email: String,
                   _roles: List<String>
    ) : EntityUser {

        val entity : EntityUser = EntityUser(email = _email, name = _name, roles = _roles)

        userRepository.save(entity)

        return entity
    }
}