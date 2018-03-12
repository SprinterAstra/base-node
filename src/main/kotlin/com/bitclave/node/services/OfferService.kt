package com.bitclave.node.services

import com.bitclave.node.repository.RepositoryStrategy
import com.bitclave.node.repository.RepositoryStrategyType
import com.bitclave.node.repository.models.Offer
import com.bitclave.node.repository.offer.OfferRepository
import com.bitclave.node.services.errors.BadArgumentException
import com.bitclave.node.services.errors.NotFoundException
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class OfferService(
        private val offerRepository: RepositoryStrategy<OfferRepository>
) {

    fun putOffer(
            id: Long,
            owner: String,
            offer: Offer,
            strategy: RepositoryStrategyType
    ): CompletableFuture<Offer> {

        return CompletableFuture.supplyAsync({
            if (id > 0) {
                val checkOffer = offerRepository.changeStrategy(strategy).findByIdAndOwner(id, owner)
                checkOffer ?: throw BadArgumentException()
            }

            if (offer.compare.isEmpty() ||
                    offer.compare.size != offer.rules.size ||
                    offer.description.isEmpty() ||
                    offer.title.isEmpty() ||
                    offer.tags.isEmpty()) {
                throw BadArgumentException()
            }

            for (item: String in offer.compare.keys) {
                if (!offer.rules.containsKey(item)) {
                    throw BadArgumentException()
                }
            }

            val putOffer = Offer(id,
                    owner,
                    offer.description,
                    offer.title,
                    offer.imageUrl,
                    offer.tags,
                    offer.compare,
                    offer.rules
            )

            offerRepository.changeStrategy(strategy).saveOffer(putOffer)

        })
    }

    fun deleteOffer(
            id: Long,
            owner: String,
            strategy: RepositoryStrategyType
    ): CompletableFuture<Long> {

        return CompletableFuture.supplyAsync({
            val deletedId = offerRepository.changeStrategy(strategy).deleteOffer(id, owner)
            if (deletedId == 0L) {
                throw NotFoundException()
            }
            return@supplyAsync deletedId
        })
    }

    fun getOffers(
            id: Long,
            owner: String,
            strategy: RepositoryStrategyType
    ): CompletableFuture<List<Offer>> {

        return CompletableFuture.supplyAsync({
            val repository = offerRepository.changeStrategy(strategy)

            if (id > 0 && owner != "0x0") {
                val offer = repository.findByIdAndOwner(id, owner)

                if (offer != null) {
                    return@supplyAsync arrayListOf(offer)
                }
                return@supplyAsync emptyList<Offer>()

            } else if (owner != "0x0") {
                return@supplyAsync repository.findByOwner(owner)

            } else {
                return@supplyAsync repository.findAll()
            }
        })
    }

}