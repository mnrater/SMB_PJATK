package com.example.projekt1

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt1.databinding.ListElementBinding
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyAdapter(val context: Context, val list: ArrayList<List<String>>, val ref: DatabaseReference) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var products = emptyList<Product>()
    init {
        ref.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previous: String?) {
                CoroutineScope(IO).launch {

                    val values  = snapshot.value as List<String>
                    val product_list = listOf(snapshot.key.toString(), values[0], values[1], values[2])
                    list.add(product_list)
                    withContext(Main) {
                        notifyDataSetChanged()
                    }
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previous: String?) {
                CoroutineScope(IO).launch {
                    list.remove(snapshot.value as List<String>)
                    val values  = snapshot.value as List<String>
                    val product_list = listOf(snapshot.key.toString(), values[0], values[1], values[2])
                    list.remove(product_list)
                    list.add(product_list)
                    withContext(Main) {
                        notifyDataSetChanged()
                    }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                CoroutineScope(IO).launch {
                    val values  = snapshot.value as List<String>
                    val product_list = listOf(snapshot.key.toString(), values[0], values[1], values[2])
                    list.remove(product_list)
                    withContext(Main) {
                        notifyDataSetChanged()
                    }
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previous: String?) {
                Log.i("MyAdapter", "Failed to delete value.")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MyAdapter", "Failed to delete value.", error.toException())
            }
        })
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ListElementBinding.inflate(inflater)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        val current_list = list[position]

        holder.binding.rvTvName.text = current_list[1]
        holder.binding.rvTvPrice.text = current_list[2]
        holder.binding.rvTvQuantity.text = current_list[3]
        if(current_list[3]=="false"){
            holder.binding.rvCbIsBought.isChecked = false
        }
        if (current_list[3]=="true"){
            holder.binding.rvCbIsBought.isChecked = true
        }

        holder.binding.rvTvName.setOnClickListener{
            ref.orderByKey().equalTo(current_list[0])
                .addListenerForSingleValueEvent(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        CoroutineScope(IO).launch {
                            snapshot.children.iterator().next().ref.removeValue()

                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.e("MyAdapter", "Failed to delete value.", error.toException())
                    }
                })
        }

        holder.binding.rvCbIsBought.setOnCheckedChangeListener{ _, isChecked ->
            ref.orderByKey().equalTo(current_list[0])
                    .addListenerForSingleValueEvent(object: ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            CoroutineScope(IO).launch {
                                val mega = snapshot.children.iterator().next().getValue() as ArrayList<String>
                                if(isChecked){
                                    mega[3] = "true"
                                }
                                else{
                                    mega[3] = "false"
                                }
                                snapshot.children.iterator().next().ref.setValue(mega)
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.e("MyAdapter", "Failed to delete value.", error.toException())
                        }
                    })
        }

    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root)

//    fun setProducts(products: List<Product>) {
//        this.products = products
//        notifyDataSetChanged()
//    }
}