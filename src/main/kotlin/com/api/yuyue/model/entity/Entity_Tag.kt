package com.api.yuyue.model.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "t_tag")
class EntityTag (
    @Id
    val name: String,

    @ManyToMany(mappedBy = "tags")
    var article: Set<EntityProgram>? = null
)