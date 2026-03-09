package com.jahid.inventory_api.mapper

import com.jahid.inventory_api.dto.CategoryResponse
import com.jahid.inventory_api.model.Category

fun Category.toCategoryResponse(): CategoryResponse {
    return CategoryResponse(
        id = this.id,
        name = this.name,
        description = this.description,
    )
}