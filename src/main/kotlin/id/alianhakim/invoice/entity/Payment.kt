package id.alianhakim.invoice.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@SQLDelete(sql = "UPDATE payment SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
data class Payment(
    @ManyToOne
    @JoinColumn(name = "id_virtual_account")
    private val virtualAccount: VirtualAccount,

    @NotNull
    @NotEmpty
    val transactionTime: LocalDateTime,

    @NotNull
    @NotEmpty
    val providerReference: String,

    @NotNull
    @Min(1)
    val amount: BigDecimal
) : BaseEntity()
