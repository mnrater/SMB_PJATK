package com.example.projekt1_smb2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt1_smb2.databinding.ListElementBinding
import kotlinx.android.synthetic.main.activity_list.*

class ProductsAdapter(val productViewModel: ProductViewModel): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var products = emptyList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ListElementBinding.inflate(inflater)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val currentProduct = products[position]
        holder.binding.rvTv1.text = currentProduct.name
        holder.binding.rvTv2.text = currentProduct.price.toString()
        holder.binding.rvTv3.text = currentProduct.quantity.toString()
        holder.binding.rvCb1.isChecked = currentProduct.bought
        holder.binding.rvTv1.setOnClickListener {
            productViewModel.delete(currentProduct)
        }
        holder.binding.btdel.setOnClickListener {
            productViewModel.delete(currentProduct)
        }
//        holder.binding.bted.setOnClickListener{
//            productViewModel.update(currentProduct)
    }
//        holder.binding.bted.setOnClickListener {
//            productViewModel.update(Product(et1.text.toString(), et2.text.toString(), et3.text.toString(), false)
//        }

    override fun getItemCount(): Int = products.size

    inner class ViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root)

    fun setProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }
}