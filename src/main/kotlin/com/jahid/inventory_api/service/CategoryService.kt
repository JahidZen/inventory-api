package com.jahid.inventory_api.service

import com.jahid.inventory_api.dto.CategoryRequest
import com.jahid.inventory_api.dto.CategoryResponse
import com.jahid.inventory_api.mapper.toCategoryResponse
import com.jahid.inventory_api.model.Category
import com.jahid.inventory_api.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    // manual category exception handler for proper http error
    class CategoryNotFoundException(id: Long) : RuntimeException("Category not found with id: $id")
    class CategoryAlreadyExistsException(name: String) : RuntimeException("Category $name already exists.")

    @Transactional
    fun createCategory(request: CategoryRequest): CategoryResponse {
        if (categoryRepository.existsByName(request.name)) { // declared existsByName in CategoryRepository
            throw CategoryAlreadyExistsException(request.name)
        }

        val category = Category(
            name = request.name,
            description = request.description,
        )
        val savedCategory = categoryRepository.save(category)
        return savedCategory.toCategoryResponse() // toCategoryResponse() is a mapper I manually created
    }



    @Transactional
    fun deleteCategory(id: Long) {
        val category = categoryRepository.findById(id).orElseThrow { CategoryNotFoundException(id) }
        categoryRepository.delete(category)
    }


    @Transactional
    fun updateCategory(id: Long, request: CategoryRequest): CategoryResponse {
        val findCategory = categoryRepository.findById(id).orElseThrow { CategoryNotFoundException(id) }
        if (categoryRepository.existsByName(request.name) && findCategory.name != request.name) {
            throw CategoryAlreadyExistsException(request.name)
        }

       findCategory.name = request.name
        findCategory.description = request.description

        val updatedCategory = categoryRepository.save(findCategory)
        return updatedCategory.toCategoryResponse()
    }



    fun getAllCategories(): List<CategoryResponse> {
        return categoryRepository.findAll().map { it.toCategoryResponse() }
    }


    fun getCategory(id: Long): CategoryResponse {
        val category = categoryRepository.findById(id).orElseThrow { CategoryNotFoundException(id)}
        return category.toCategoryResponse()
    }
}