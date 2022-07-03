package id.alianhakim.invoice.dao

import id.alianhakim.invoice.entity.InvoiceType
import org.springframework.data.repository.PagingAndSortingRepository

interface InvoiceTypeDao : PagingAndSortingRepository<InvoiceType, String>