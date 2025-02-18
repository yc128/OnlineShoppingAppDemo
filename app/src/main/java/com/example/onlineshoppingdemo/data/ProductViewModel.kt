package com.example.onlineshoppingdemo.data


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshoppingdemo.R
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val productDao = ProductDatabase.getDatabase(application).productDao()
    private val repository = ProductRepository(productDao)

    val products: StateFlow<List<Product>> = repository.products
        .map { entities -> entities.map { Product(it.id, it.name, it.price, it.description) } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // insert several products into the database for display
    init {

        viewModelScope.launch {
            if (repository.products.stateIn(viewModelScope).value.isEmpty()) {
                repository.insertProducts(
                    listOf(
                        ProductEntity(name = "Product_1", price = 150.0,
                            description = "This is a sample description for Product_1",
                            imageResId = R.drawable.product1
                        ),
                        ProductEntity(name = "Product_2", price = 180.0,
                            description = "This is a sample description for for Product_2",
                            imageResId = R.drawable.product2
                        ),
                        ProductEntity(name = "Product_3", price = 120.0,
                            description = "This is a sample description for for Product_3",
                            imageResId = R.drawable.product3
                        ),
                    )
                )
            }
        }
    }
}
