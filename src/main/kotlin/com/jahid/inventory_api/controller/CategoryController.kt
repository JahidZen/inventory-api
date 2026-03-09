package com.jahid.inventory_api.controller

import com.jahid.inventory_api.dto.CategoryRequest
import com.jahid.inventory_api.dto.CategoryResponse
import com.jahid.inventory_api.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @GetMapping
    fun getAllCategoriesController(): ResponseEntity<List<CategoryResponse>> {
        val categories = categoryService.getAllCategories()
        return ResponseEntity.ok(categories)
    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<CategoryResponse> {
        val category = categoryService.getCategory(id)
        return ResponseEntity.ok(category)
    }

    @PostMapping
    fun createCategory(@RequestBody request: CategoryRequest): ResponseEntity<CategoryResponse> {
        val category = categoryService.createCategory(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(category)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Long, @RequestBody request: CategoryRequest): ResponseEntity<CategoryResponse> {
        val category = categoryService.updateCategory(id, request)
        return ResponseEntity.ok(category)
    }

    @DeleteMapping("    /{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<CategoryResponse> {
        val category = categoryService.deleteCategory(id)
        return ResponseEntity.noContent().build()
    }
}