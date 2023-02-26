package com.example.lospolloshermanos.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lospolloshermanos.*
import com.example.lospolloshermanos.data.ListOfMenuItems
import com.example.lospolloshermanos.food_model.FoodItem

class FoodItemAdapter() :
  RecyclerView.Adapter<FoodItemAdapter.FoodViewHolder>()
    {
        private val dataset = ListOfMenuItems.items

        class FoodViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val foodPicture: ImageView = view.findViewById<ImageView>(R.id.food_picture)
            val foodName: TextView = view.findViewById<TextView>(R.id.food_name)
            val foodDescription: TextView = view.findViewById<TextView>(R.id.food_description)
            val foodPrice: TextView = view.findViewById<TextView>(R.id.food_price)
            val submitButton: Button = view.findViewById<Button>(R.id.add_item)
    }

        override fun getItemCount(): Int = dataset.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
            val layout = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.itemview, parent, false)
            return FoodViewHolder(layout)
        }

        override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
            val item = dataset[position]
            val context = holder.view.context
            holder.foodPicture.setImageResource(item.imageResourceId)
            holder.foodName.text = item.name
            holder.foodDescription.text = item.description
            holder.foodPrice.text = context.getString(R.string.item_price, item.price)
            holder.submitButton.setOnClickListener {
                val action = MenuFragmentDirections.actionMenuToOrderDetailsAddExtras(dish = holder.foodName.text.toString(),
                                                                                        price = item.price.toFloat())
                holder.view.findNavController().navigate(action)
            }
        }
}