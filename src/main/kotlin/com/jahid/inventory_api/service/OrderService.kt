package com.jahid.inventory_api.service

import com.jahid.inventory_api.dto.OrderRequest
import com.jahid.inventory_api.dto.OrderResponse
import com.jahid.inventory_api.model.Order
import com.jahid.inventory_api.model.OrderItem
import com.jahid.inventory_api.repository.OrderRepository
import com.jahid.inventory_api.repository.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal
import com.jahid.inventory_api.mapper.toOrderResponse

@Transactional
@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
) {
    @Transactional
    fun placeOrder(request: OrderRequest): OrderResponse {
        val order = Order(customerEmail = request.email)
        var total = BigDecimal.ZERO

        for ((productId, quantity) in request.items) {
            val getProduct = productRepository.findById(productId).orElseThrow {
                RuntimeException("Product with id $productId cannot be found")
            }

            if (getProduct.stock < quantity) {
                throw RuntimeException("Not in stock")
            }

            getProduct.stock -= quantity
            productRepository.save(getProduct)

            val orderItem = OrderItem(
                product = getProduct,
                quantity = quantity.toLong(),
                priceAtPurchase = getProduct.price
            )

            order.addItem(orderItem)
            total += (getProduct.price * quantity.toBigDecimal())
        }
        order.totalAmount = total
        orderRepository.save(order)
        return order.toOrderResponse()
    }
}