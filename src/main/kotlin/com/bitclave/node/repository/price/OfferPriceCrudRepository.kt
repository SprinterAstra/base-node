package com.bitclave.node.repository.price

import com.bitclave.node.repository.models.OfferPrice
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface OfferPriceCrudRepository : CrudRepository<OfferPrice, Long> {
    fun findById(id: Long): OfferPrice?
    fun findByOfferId(id: Long): List<OfferPrice>
}