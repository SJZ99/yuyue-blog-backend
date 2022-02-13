package com.api.yuyue.model.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "t_lang")
class EntityLanguage (

    @Id
    @Column(length = 60)
    val language : String,

    @Column(length = 1500)
    var description : String = ""
)

@Entity
@Table(name = "t_program")
class EntityProgram (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null,

    @Column(length = 60)
    var lang : String,

    @Column(length = 90)
    var title : String,

    @Column(columnDefinition = "TEXT") //64KB -> 21333個中文字
    var content : String,

    @Column(columnDefinition = "TINYTEXT") //256 byte -> 85個中文字
    var preface : String,

    var updateOn : LocalDate = LocalDate.now(),

    @ElementCollection(fetch = FetchType.EAGER)
    var tags : Set<String> = setOf(),

    @Column(columnDefinition = "TINYTEXT")
    var img : String?,

    var publish: Boolean = false,
)

interface EntityProgramPreview {
    val id : Int?
    val img : String
    val title :String
    val preface : String
    val updateOn : LocalDate
}