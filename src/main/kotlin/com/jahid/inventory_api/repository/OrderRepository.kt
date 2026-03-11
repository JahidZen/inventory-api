package com.jahid.inventory_api.repository

import com.jahid.inventory_api.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long>