package com.jobin.grocery_app_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CountInterface, ClickInterface, DeleteInterface {

    lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val productAdapter = ProductAdapter(this,this,this,this)
        recyclerView.adapter = productAdapter

        productViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            ProductViewModel::class.java)
        productViewModel.getAllProductList.observe(this,{list->
            list?.let {
                productAdapter.updateList(it)
            }
        })

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCount(count: Int) {
        textView3.setText(count.toString())
    }

    override fun onClick(productModel: ProductModel) {
        val intent = Intent(this, AddEditActivity::class.java)
        intent.putExtra("type","Edit")
        intent.putExtra("productId",productModel.id)
        intent.putExtra("productName",productModel.productName)
        intent.putExtra("productPrice",productModel.productPrice)
        startActivity(intent)
    }

    override fun onDelete(productModel: ProductModel) {
        productViewModel.deleteProduct(productModel)
        Toast.makeText(this,"Product deleted successfully", Toast.LENGTH_LONG).show()
    }
}