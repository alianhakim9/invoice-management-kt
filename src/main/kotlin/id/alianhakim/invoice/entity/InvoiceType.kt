package id.alianhakim.invoice.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Entity
@SQLDelete(sql = "UPDATE invoice_type SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
data class InvoiceType(
    @NotEmpty
    @Size(min = 3, max = 100)
    val code: String,

    @NotEmpty
    @Size(min = 3, max = 100)
    var name: String,

    @ManyToMany(
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "invoice_type_provider",
        joinColumns = [JoinColumn(name = "id_invoice_type")],
        inverseJoinColumns = [JoinColumn(name = "id_payment_provider")]
    )
    val paymentProvider: Set<PaymentProvider> = HashSet()
) : BaseEntity()
