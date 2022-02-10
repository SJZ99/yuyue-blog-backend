package com.api.yuyue.model.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User
import javax.persistence.*

@Entity
@Table(name = "t_user")
class Entity_User(

    @Id
    @Column(length = 90)
    var email : String,

    @JvmField
    @Column(length = 50)
    var name : String,

    @Transient
    var roles : List<String> = mutableListOf("user")

) : OAuth2User {

    fun setName(name : String) {
        this.name = name
    }

    override fun getName(): String {
        return this.name
    }

    override fun getAttributes(): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["email"] = email
        map["name"] = name

        return map
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.map {
                value -> GrantedAuthority { value }
        }.toMutableList()
    }
}