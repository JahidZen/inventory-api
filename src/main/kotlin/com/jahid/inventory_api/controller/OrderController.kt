package com.jahid.inventory_api.controller

import com.jahid.inventory_api.dto.OrderRequest
import com.jahid.inventory_api.dto.OrderResponse
import com.jahid.inventory_api.model.Order
import com.jahid.inventory_api.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping
    fun placeOrder(@RequestBody order: OrderRequest): ResponseEntity<OrderResponse> {
        return ResponseEntity<OrderResponse>.ok().body(orderService.placeOrder(order))
    }
}