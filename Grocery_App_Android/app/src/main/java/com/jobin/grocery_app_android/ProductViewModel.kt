package com.jobin.grocery_app_android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {

    val getAllProductList:LiveData<List<ProductModel>>
    val productRepository: ProductRepository

    init {
        val dao = ProductDatabase.getDatabase(application).getProductDao()
        productRepository = ProductRepository(dao)
        getAllProductList = productRepository.getAllProduct
    }

    fun productInsert(productModel: ProductModel) = viewModelScope.launch {
        productRepository.insert(productModel)
    }

    fun productUpdate(productModel: ProductModel) = viewModelScope.launch {
        productRepository.update(productModel)
    }

    fun deleteProduct(productModel: ProductModel) = viewModelScope.launch {
        productRepository.delete(productModel)
    }

}