package com.liliputdev.mvvmexample.repository.retrofit.apiModel

/**
 * Created by Masood Dalman on 6/29/2022.
 */
typealias APIModelAllProduct = ArrayList<APIModelAllProductElement>

data class APIModelAllProductElement (
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: Category,
    val image: String,
    val rating: Rating
)

enum class Category {
    Electronics,
    Jewelery,
    MenSClothing,
    WomenSClothing
}

data class Rating (
    val rate: Double,
    val count: Long
)
