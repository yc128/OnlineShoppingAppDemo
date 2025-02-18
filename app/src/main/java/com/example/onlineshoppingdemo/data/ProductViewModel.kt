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

/**
 * ViewModel for managing the product list in the shopping application.
 *
 * This ViewModel is responsible for interacting with the repository layer to retrieve and manage
 * a list of products. It exposes the product list to the UI layer as a `StateFlow`, which allows the
 * UI to reactively update whenever the list changes.
 *
 * **Key responsibilities:**
 * - Fetch the list of products from the database through the repository.
 * - Expose the product list to the UI in a reactive `StateFlow` so that the UI automatically
 *   updates when the list changes.
 * - Insert some sample products into the database for display if the product list is empty.
 *
 * **ViewModel and Repository interaction:**
 * - The ViewModel communicates with the `ProductRepository`, which handles the data access logic.
 * - The list of products is exposed as a `StateFlow`, ensuring that the UI gets updated automatically
 *   whenever the data changes.
 * - If the product list is empty, some sample products are inserted into the database as a default set.
 */
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
