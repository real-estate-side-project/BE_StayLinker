package com.yoong.sunnyside.domain.community.comment.entity

import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.consumer.entity.Consumer
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "community_reply")
class CommunityReply(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    val comment: CommunityComment,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", nullable = false)
    val consumer: Consumer,

    var description: String,

    ) {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "created_at",nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at",nullable = false)
    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "deleted_at",nullable = true)
    var deletedAt: LocalDateTime? = null

    constructor(replyRequest: ReplyRequest, communityComment: CommunityComment, consumer: Consumer) : this(
        comment = communityComment,
        consumer = consumer,
        description = replyRequest.description
    )

    fun update(replyRequest: ReplyRequest) {

        this.description = replyRequest.description
    }

    fun delete(){
        this.deletedAt = LocalDateTime.now()
    }
}