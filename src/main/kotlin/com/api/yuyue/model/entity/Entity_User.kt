package com.api.yuyue.model.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User
import javax.persistence.*

@Entity
@Table(name = "t_user")
class Entity_User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0,

    @Column(length = 90)
    var email : String,

    @JvmField
    @Column(length = 50)
    var name : String,

    @Column(length = 30)
    var role : String = "User"

) : OAuth2User {

    fun setName(name : String) {
        this.name = name
    }

    override fun getName(): String {
        return this.name
    }

    override fun getAttributes(): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["id"] = id
        map["email"] = email
        map["name"] = name
        map["role"] = role

        return map
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableSetOf( GrantedAuthority { role } )
    }
}