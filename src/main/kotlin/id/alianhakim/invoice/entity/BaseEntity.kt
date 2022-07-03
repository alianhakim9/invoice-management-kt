package id.alianhakim.invoice.entity

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    val id: String? = null

    @field:CreatedBy
    var createdBy: String? = null

    @field:LastModifiedBy
    var updatedBy: String? = null

    @field:CreatedDate
    var createdAt: LocalDateTime? = null

    @field:LastModifiedDate
    var updatedAt: LocalDateTime? = null

    @NotNull
    @Enumerated(EnumType.STRING)
    var statusRecord: StatusRecord = StatusRecord.ACTIVE
}
