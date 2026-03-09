package com.jahid.inventory_api.dto

import jakarta.persistence.Column

data class CategoryRequest(
    var name: String,

    @Column(columnDefinition = "TEXT")
    var description: String
)
