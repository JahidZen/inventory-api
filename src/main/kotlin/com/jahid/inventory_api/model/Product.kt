package com.jahid.inventory_api.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.JoinTable
import java.math.BigDecimal
import jakarta.persistence.ManyToMany
import jakarta.persistence.JoinColumn
import jakarta.persistence.Version

@Entity
@Table(name = "products")
data class Product (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var price: BigDecimal,

    var sku: String = "",

    // how many items
    var stock: Int = 0,

    @Version // for handling concurrency
    var version: Long = 0,

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "product_categories",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )

    var categories: Set<Category> = HashSet()
)
