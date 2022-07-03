package id.alianhakim.invoice.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@SQLDelete(sql = "UPDATE invoice SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
data class Invoice(
    @NotEmpty
    @NotNull
    @Size(min = 3, max = 100)
    val invoiceNumber: String,

    val dueDate: LocalDate,

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    val description: String,

    @NotNull
    @Min(0)
    val amount: BigDecimal,

    @NotNull
    val paid: Boolean = false
) : BaseEntity()
