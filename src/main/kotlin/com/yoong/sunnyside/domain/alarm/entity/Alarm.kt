package com.yoong.sunnyside.domain.alarm.entity

import com.yoong.sunnyside.common.entity.ProjectFunc
import com.yoong.sunnyside.infra.security.MemberRole
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "alarm")
class Alarm(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_func_id")
    var projectFunc: ProjectFunc,

    val memberId: Long,

    val memberRole: MemberRole,

    val message: String,

    val isRead: Boolean,
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    val createAt: LocalDateTime = LocalDateTime.now()
}