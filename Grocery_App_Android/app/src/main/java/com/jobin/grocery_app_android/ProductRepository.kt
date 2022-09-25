package com.jobin.grocery_app_android

import androidx.lifecycle.LiveData
import com.jobin.grocery_app_android.ProductDao
import com.jobin.grocery_app_android.ProductModel

class ProductRepository(private val productDao: ProductDao) {

    val getAllProduct:LiveData<List<ProductModel>> = productDao.getAllProducts()

    suspend fun insert(productModel: ProductModel){
        productDao.insert(productModel)
    }

    suspend fun update(productModel: ProductModel){
        productDao.update(productModel)
    }

    suspend fun delete(productModel: ProductModel){
        productDao.delete(productModel)
    }

}