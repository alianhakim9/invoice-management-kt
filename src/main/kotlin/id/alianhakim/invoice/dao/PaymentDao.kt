package id.alianhakim.invoice.dao

import id.alianhakim.invoice.entity.Payment
import org.springframework.data.repository.PagingAndSortingRepository

interface PaymentDao : PagingAndSortingRepository<Payment, String>