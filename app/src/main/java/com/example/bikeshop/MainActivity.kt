package com.example.bikeshop

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore



class MainActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firestore
        db = FirebaseFirestore.getInstance()

        // Create a sample product
        val product = Product(
            id = "product_001",
            name = "Mountain Bike",
            price = 350.0,
            quantity = 10
        )

        // Add product to "Products" collection in Firestore
        db.collection("Products")
            .document(product.id)
            .set(product)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseTest", "Product added successfully")
                } else {
                    Log.e("FirebaseTest", "Error adding product: ${task.exception}")
                }
            }
    }
}
