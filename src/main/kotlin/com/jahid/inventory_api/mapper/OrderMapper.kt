package com.jahid.inventory_api.mapper

import com.jahid.inventory_api.dto.OrderItemResponse
import com.jahid.inventory_api.dto.OrderResponse
import com.jahid.inventory_api.model.Order
import com.jahid.inventory_api.model.OrderItem

fun Order.toOrderResponse() =  OrderResponse(
    orderId = this.id,
    email = this.customerEmail,
    amount = totalAmount,
    status = this.status,
    orderedItems = this.items.map { it.toOrderItemResponse() }
)


fun OrderItem.toOrderItemResponse() = OrderItemResponse(
    productId = this.product.id,
    productName = this.product.name,
    quantity = this.quantity.toInt(),
    priceAtPurchase = this.priceAtPurchase
)