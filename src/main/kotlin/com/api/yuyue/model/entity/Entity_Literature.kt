package com.api.yuyue.model.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "t_series")
class Entity_Series (

    @Id
    var id : Int,

    @Column(length = 90)
    var topic : String,

    var amount : Int,

    @Column(columnDefinition = "TINYTEXT")
    var img : String
)

@Entity
@Table(name = "t_literature")
class Entity_Literature (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int,

    @Column(length = 90)
    var series : String,

    @Column(length = 90)
    var title : String,

    @Column(columnDefinition = "TEXT") //64KB -> 21333個中文字
    var content : String,

    @Column(columnDefinition = "TINYTEXT") //256 byte -> 85個中文字
    var preface : String,

    var updateOn : LocalDate,

    @Column(columnDefinition = "TINYTEXT")
    var img : String
)

interface Entity_Literature_Preview {
    fun getId() : Int
    fun getImg() : String
    fun getTitle() :String
    fun getPreface() : String
    fun getUpdateOn() : LocalDate
}