package id.alianhakim.invoice.dao

import id.alianhakim.invoice.entity.InvoiceType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql

@SpringBootTest
@Sql(
    scripts = [
        "/sql/delete-invoice-type.sql",
        "/sql/insert-inactive-invoice-type.sql"
    ]
)
class InvoiceTypeDaoTests constructor(
    @Autowired val invoiceTypeDao: InvoiceTypeDao
) {

    @Test
    fun `should insert invoice type`() {
        // given
        var invoiceType = InvoiceType(
            code = "IT-001",
            name = "Invoice Type Test",
        )

        // when
        Assertions.assertNull(invoiceType.id)
        invoiceTypeDao.save(invoiceType)

        // then
        invoiceType.let {
            Assertions.assertNotNull(it.id)
            Assertions.assertNotNull(it.createdAt)
            Assertions.assertNotNull(it.updatedAt)
            Assertions.assertNotNull(it.createdBy)
            Assertions.assertNotNull(it.updatedBy)
            Assertions.assertNotNull(it.statusRecord)
            Assertions.assertEquals(it.createdAt, it.updatedAt)
        }

        Thread.sleep(1000)

        invoiceType.name = "Test Update"
        invoiceType = invoiceTypeDao.save(invoiceType)

        invoiceType.let {
            Assertions.assertNotSame(it.createdAt, it.updatedAt)
        }
    }

    @Test
    fun `should test query soft delete`() {
        // given
        val recordCount = invoiceTypeDao.count()

        // when

        // then
        Assertions.assertEquals(1, recordCount)
    }

    @Test
    fun `should soft delete`() {
        // given
        val invoiceType: InvoiceType = invoiceTypeDao.findById("test-02").get()

        // when
        invoiceTypeDao.delete(invoiceType)
        val recordCount = invoiceTypeDao.count()

        // then
        Assertions.assertEquals(0, recordCount)

    }

}