package com.jahid.inventory_api.dto

import com.jahid.inventory_api.model.OrderItem
import com.jahid.inventory_api.model.Product

data class OrderRequest(
    var email: String,
    var items: Map<Long, Int> //
)