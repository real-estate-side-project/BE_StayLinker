package com.yoong.sunnyside.common.entity

import jakarta.persistence.*

@Entity
@Table(name = "project_func")
class ProjectFunc(
    val name: String,
    val code: String,
    val level: Int,
    val parentId: Int,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null
}