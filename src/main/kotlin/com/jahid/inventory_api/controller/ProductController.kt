package com.jahid.inventory_api.controller

import com.jahid.inventory_api.dto.ProductRequest
import com.jahid.inventory_api.dto.ProductResponse
import com.jahid.inventory_api.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {
    @PostMapping
    fun createProduct(@RequestBody request: ProductRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request))
    }

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts())
    }


    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(id))
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody request: ProductRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, request))
    }


    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    // buying product
    @PostMapping("/{id}/buy")
    fun buyProduct(@PathVariable id: Long, @RequestParam quantity: Int): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.buyProduct(id, quantity))
    }

    // updating stock
    @PutMapping("/{id}/stockUpdate")
    fun stockUpdate(@PathVariable id: Long, @RequestParam quantity: Int): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.stockUpdate(id, quantity))
    }
}