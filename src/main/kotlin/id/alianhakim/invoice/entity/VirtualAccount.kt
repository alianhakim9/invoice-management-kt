package id.alianhakim.invoice.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@SQLDelete(sql = "UPDATE virtual_account SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
data class VirtualAccount(
    @ManyToOne
    @JoinColumn(name = "id_payment_provider")
    val paymentProvider: PaymentProvider,

    @NotNull
    @NotEmpty
    @Size(min = 1)
    val companyId: String,

    @NotNull
    @NotEmpty
    @Size(min = 1)
    val accountNumber: String,

    @NotNull
    @Enumerated(EnumType.STRING)
    val virtualAccountType: VirtualAccountType = VirtualAccountType.CLOSED
) : BaseEntity()
