package id.alianhakim.invoice.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.Entity
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Entity
@SQLDelete(sql = "UPDATE payment_provider SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
data class PaymentProvider(
    @NotEmpty
    @Size(min = 3, max = 100)
    val code: String,

    @NotEmpty
    @Size(min = 3, max = 100)
    val name: String,

    val logo: String
) : BaseEntity()
