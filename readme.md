#  Shopping App Demo

This is a sample Android application that demonstrates the basic functionalities of an shopping platform, including displaying products, adding items to the cart, viewing the cart, and a simulating checkout process.

## Features
- **Product List:** Display a list of products fetched from a local database.
- **Product Detail Page:** View the details of a specific product by tapping on an item.
- **Add to Cart:** Add products to the shopping cart and view the cart contents.
- **Cart Management:** Check product quantities in the cart and remove items.
- **Checkout:** Finalize the order and display the total price.

## Technologies Used
- **Kotlin**: Programming language for Android development.
- **Jetpack Compose**: UI toolkit for building native UIs in a declarative way.
- **Room Database**: A local database to store products and cart data.
- **MVVM Architecture**: To separate concerns and ensure a clean, testable architecture.


## Requirements
- Android Studio with Kotlin support.
- Android SDK 35 or higher.
- Jetpack Compose dependencies.


## Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/yc128/OnlineShoppingAppDemo
```

### 2. Open the project
Open the project in Android Studio.

### 3. Sync Gradle
Ensure that all dependencies are correctly synced.

### 4. Build and Run
Click on **Run** to build and run the application on an emulator or a physical device.

## How It Works

1. **Product List (`MainScreen`)**:
   - The app displays a list of products in a `LazyColumn` with basic information such as name and price.
   - Clicking on an item navigates to the product detail page.

2. **Product Detail Page (`ProductDetailScreen`)**:
   - Displays detailed information about a selected product.
   - Includes an "Add to Cart" button to add the product to the cart.

3. **Cart (`CartScreen`)**:
   - Displays a list of products added to the cart, along with their quantities and total price.
   - The cart allows users to manage items.
   - The total price is dynamically updated as items are added or removed.

4. **Checkout**:
   - A simple checkout screen that displays the total price of the order.


