package com.jahid.inventory_api.repository

import com.jahid.inventory_api.model.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository: JpaRepository<OrderItem, Long>