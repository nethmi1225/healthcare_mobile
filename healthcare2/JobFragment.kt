package com.nibm.healthcare2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class JobFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var adapter: ProductAdapter

    // Sample product list (replace with your actual data source)
    private val productList = listOf(
        Product("Bandages", "Basic wound care"),
        Product("Thermometer", "Digital temperature reader"),
        Product("Pain Relief", "Ibuprofen tablets"),
        Product("Face Masks", "Disposable masks"),
        Product("Vitamins", "Daily supplements")
    )
    private var filteredList = productList.toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_job, container, false)

        // Initialize views
        searchBar = view.findViewById(R.id.searchBar)
        recyclerView = view.findViewById(R.id.productRecyclerView)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(filteredList) { product ->
            Toast.makeText(requireContext(), "${product.name} added to cart!", Toast.LENGTH_SHORT).show()
            // Add your cart logic here (e.g., save to a list or database)
        }
        recyclerView.adapter = adapter

        // Search functionality
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterProducts(s.toString())
            }
        })

        return view
    }

    private fun filterProducts(query: String) {
        filteredList.clear()
        filteredList.addAll(
            if (query.isEmpty()) productList
            else productList.filter { it.name.contains(query, ignoreCase = true) }
        )
        adapter.notifyDataSetChanged()
    }
}

// Data class for a product
data class Product(val name: String, val description: String)

// RecyclerView Adapter
class ProductAdapter(
    private val products: MutableList<Product>,
    private val onAddToCartClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName = itemView.findViewById<TextView>(R.id.productName)
        private val addToCartButton = itemView.findViewById<Button>(R.id.addToCartButton)

        fun bind(product: Product) {
            productName.text = product.name
            itemView.contentDescription = "Healthcare product: ${product.name}"
            addToCartButton.setOnClickListener { onAddToCartClick(product) }
        }
    }
}