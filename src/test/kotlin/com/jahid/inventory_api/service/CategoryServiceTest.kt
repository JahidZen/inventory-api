package com.jahid.inventory_api.service

import com.jahid.inventory_api.dto.CategoryRequest
import com.jahid.inventory_api.model.Category
import com.jahid.inventory_api.repository.CategoryRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.Optional

class CategoryServiceTest {
    private lateinit var categoryService: CategoryService
    private lateinit var categoryRepository: CategoryRepository

    @BeforeEach
    fun setup() {
        categoryRepository = mock()
        categoryService = CategoryService(categoryRepository)
    }


    @Test
    fun `should create category`() {
        val request = CategoryRequest("Electronics", "All electric devices")
        val category = Category(
            id = 1,
            name = "Electronics",
            description = "All electric devices"
        )

        whenever(categoryRepository.existsByName("Electronics")).thenReturn(false)
        whenever(categoryRepository.save(any())).thenReturn(category)

        val result = categoryService.createCategory(request)
        assertEquals("Electronics", result.name)
    }



    @Test
    fun `throw exception if category name already exists`() {
        val request = CategoryRequest("Electronics", "All electric devices")
        whenever(categoryRepository.existsByName("Electronics")).thenReturn(true)


        assertThrows<CategoryService.CategoryAlreadyExistsException> {
            categoryService.createCategory(request)
        }
    }





    @Test
    fun `throw exception if category id does not exists`() {
        val request = CategoryRequest("Electronics", "All electric devices")
        whenever(categoryRepository.findById(1)).thenReturn(Optional.empty())

        assertThrows<CategoryService.CategoryNotFoundException> {
            categoryService.deleteCategory(1)
        }
    }






    @Test
    fun `should update category`() {
        val existingCategory = Category(1, name = "Electronics", description = "All electric devices")

        val request = CategoryRequest("Tech", "Technology")

        whenever(categoryRepository.findById(1)).thenReturn(Optional.of(existingCategory))
        whenever(categoryRepository.existsByName("Tech")).thenReturn(false)
        whenever(categoryRepository.save(any())).thenReturn(existingCategory)

        val result = categoryService.updateCategory(1, request)
        assertEquals("Tech", result.name)
        assertEquals("Technology", result.description)


    }



    @Test
    fun `should delete category`() {
        val category = Category(1, name = "Electronics", description = "All electric devices")

        whenever(categoryRepository.findById(1)).thenReturn(Optional.of(category))

        categoryService.deleteCategory(1)
        verify(categoryRepository).delete(category)
    }



    @Test
    fun `should get all categories`() {
        val category = Category(1, name = "Electronics", description = "All electric devices")
        whenever(categoryRepository.findAll()).thenReturn(listOf(category))
        val result = categoryService.getAllCategories()
        verify(categoryRepository).findAll()

        assertEquals(1, result.size)
        assertEquals("Electronics", result[0].name)
        assertEquals("All electric devices", result[0].description)
    }


    @Test
    fun `should get specific category`() {
        val category = Category(1, name = "Electronics", description = "All electric devices")
        whenever(categoryRepository.findById(1)).thenReturn(Optional.of(category))
        val result = categoryService.getCategory(1)

        verify(categoryRepository).findById(1)

        assertEquals(1, result.id)
        assertEquals("Electronics", result.name)
        assertEquals("All electric devices", result.description)
    }

}