package com.jahid.inventory_api.repository

import com.jahid.inventory_api.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>