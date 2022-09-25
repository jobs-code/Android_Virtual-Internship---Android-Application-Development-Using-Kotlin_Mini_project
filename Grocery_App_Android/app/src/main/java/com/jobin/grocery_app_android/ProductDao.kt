package com.jobin.grocery_app_android

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(productModel: ProductModel)

    @Update
    suspend fun update(productModel: ProductModel)

    @Delete
    suspend fun delete(productModel: ProductModel)

    @Query("select * from products order by id ASC")
    fun getAllProducts():LiveData<List<ProductModel>>

}