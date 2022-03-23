package com.api.yuyue.model.entity

import java.time.LocalDate

interface EntityProgramPreview {
    val id: Int?
    val img: String
    val title:String
    val preface: String
    val updateOn: LocalDate
    val isPublish: Boolean
}

interface EntityLiteraturePreview {
    val id: Int
    val img: String
    val title: String
    val preface: String
    val updateOn: LocalDate
    val publish: Boolean
}