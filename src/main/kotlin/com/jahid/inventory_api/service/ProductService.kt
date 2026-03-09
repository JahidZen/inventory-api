package com.jahid.inventory_api.service

import com.jahid.inventory_api.dto.ProductRequest
import com.jahid.inventory_api.dto.ProductResponse
import com.jahid.inventory_api.mapper.toResponse
import com.jahid.inventory_api.model.Product
import com.jahid.inventory_api.repository.CategoryRepository
import com.jahid.inventory_api.repository.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {
   @Transactional
   fun createProduct(request: ProductRequest): ProductResponse {
       val categories = categoryRepository.findAllById(request.categoryIds)

       if (categories.size != request.categoryIds.size) {
           throw RuntimeException("One or more category IDs are invalid.")
       }

       val product = Product(
           name = request.name,
           price = request.price,
           sku = request.sku,
           stock = request.stock,
           categories = categories.toSet()
       )

       val savedProduct = productRepository.save(product)


       return savedProduct.toResponse()
   }





    fun getAllProducts(): List<ProductResponse> {
        return productRepository.findAll().map { it.toResponse() }
    }





    @Transactional
    fun updateProduct(id: Long, request: ProductRequest): ProductResponse {
        val findProduct = productRepository.findById(id).orElseThrow {RuntimeException("Product not found.")}

        val categories = categoryRepository.findAllById(request.categoryIds)
        if (categories.size != request.categoryIds.size) {
            throw RuntimeException("One or more category IDs are invalid.")
        }

        findProduct.name = request.name
        findProduct.price = request.price
        findProduct.sku = request.sku
        findProduct.categories = categories.toSet()

        return findProduct.toResponse()
    }



    fun getProduct(id: Long): ProductResponse {
        val product = productRepository.findById(id).orElseThrow {RuntimeException("Product not found.")}
        return product.toResponse()
    }



    fun deleteProduct(id: Long) {
        val product = productRepository.findById(id).orElseThrow {RuntimeException("Product not found.")}
        productRepository.delete(product)
    }


    // buying product
    fun buyProduct(productId: Long, quantityToBuy: Int): ProductResponse {
        val product = productRepository.findById(productId).orElseThrow { RuntimeException("Product not found.") }

        if (product.stock < quantityToBuy) {
            throw RuntimeException("Not enough products left. Stock out !")
        }

        product.stock -= quantityToBuy

        val savedProduct = productRepository.save(product)
        return savedProduct.toResponse()
    }


    // updating stock
    fun stockUpdate(productId: Long, quantityToStock: Int): ProductResponse {
        val product = productRepository.findById(productId).orElseThrow {RuntimeException("Product not found.")}

        product.stock += quantityToStock
        val savedProduct = productRepository.save(product)
        return savedProduct.toResponse()
    }
}