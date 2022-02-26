package com.api.yuyue.model.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "t_series")
class EntitySeries (

    @Id
    var id : Int? = null,

    @Column(length = 90)
    var topic : String,

    var amount : Int = 0,

    @Column(columnDefinition = "TINYTEXT")
    var img : String = "",
)

@Entity
@Table(name = "t_literature")
class EntityLiterature (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null,

    @Column(length = 90)
    var series : String,

    @Column(length = 90)
    var title : String,

    @Column(columnDefinition = "TEXT") //64KB -> 21333個中文字
    var content : String,

    @Column(columnDefinition = "TINYTEXT") //256 byte -> 85個中文字
    var preface : String,

    var updateOn : LocalDate = LocalDate.now(),

    @Column(columnDefinition = "TINYTEXT")
    var img : String? = "",

    var publish: Boolean = false,
)

interface EntityLiteraturePreview {
    fun getId(): Int
    fun getImg(): String
    fun getTitle():String
    fun getPreface(): String
    fun getUpdateOn(): LocalDate
    fun getPublish(): Boolean
}