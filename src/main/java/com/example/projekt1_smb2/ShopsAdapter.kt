package com.example.projekt1_smb2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt1_smb2.databinding.ListElementBinding
import kotlinx.android.synthetic.main.activity_list.*

class ShopsAdapter(val shopViewModel: ShopViewModel): RecyclerView.Adapter<ShopsAdapter.ViewHolder>() {

    private var shops = emptyList<Shop>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ListElementBinding.inflate(inflater)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopsAdapter.ViewHolder, position: Int) {
        val currentShop = shops[position]
        holder.binding.rvTv1.text = currentShop.name
        holder.binding.rvTv2.text = currentShop.description.toString()
        holder.binding.rvTv3.text = currentShop.radius.toString()
        holder.binding.rvTv1.setOnClickListener {
            shopViewModel.delete(currentShop)
        }
        holder.binding.btdel.setOnClickListener {
            shopViewModel.delete(currentShop)
        }
    }

    override fun getItemCount(): Int = shops.size

    inner class ViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root)

    fun setShops(shops: List<Shop>) {
        this.shops = shops
        notifyDataSetChanged()
    }
}