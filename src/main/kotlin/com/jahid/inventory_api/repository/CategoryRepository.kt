package com.jahid.inventory_api.repository

import com.jahid.inventory_api.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByName(name: String): Category?
    fun existsByName(name: String): Boolean
}