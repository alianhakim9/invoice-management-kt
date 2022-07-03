package id.alianhakim.invoice.dao

import id.alianhakim.invoice.entity.VirtualAccount
import org.springframework.data.repository.PagingAndSortingRepository

interface VirtualAccountDao : PagingAndSortingRepository<VirtualAccount, String>