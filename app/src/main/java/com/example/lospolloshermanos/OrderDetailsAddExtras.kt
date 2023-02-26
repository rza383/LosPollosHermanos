package com.example.lospolloshermanos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.lospolloshermanos.constants.FoodConstants
import com.example.lospolloshermanos.databinding.FragmentOrderDetailsAddExtrasBinding
import com.example.lospolloshermanos.model.OrderViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderDetailsAddExtras.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderDetailsAddExtras : Fragment() {
    // TODO: Rename and change types of parameters
    companion object {
        const val DISH = "dish"
        const val PRICE = "price"
    }
    private var binding: FragmentOrderDetailsAddExtrasBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()
    private lateinit var dishName: String
    private var dishPrice = 0.0
    private var extrasPrice = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dishName = it.getString(DISH).toString()
            dishPrice = it.getFloat(PRICE).toDouble()
            sharedViewModel.addMainDish(dishName)
        }
        Log.d("addextras", "$dishName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentOrderDetailsAddExtrasBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            orderExtrasFragment = this@OrderDetailsAddExtras
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            sharedViewModel.updatePrice(dishPrice)
            extraPrice = FoodConstants.extrasPrice
            drinkPrice = FoodConstants.drinkPrice
            addCheese = getString(R.string.additional_cheese)
            addSauce = getString(R.string.additional_sauce)
            additionalDrink = getString(R.string.add_drink)
            additionalJalapeno = getString(R.string.additional_jalapeno)
            onions = getString(R.string.onion)
        }
    }

    fun setExtras(price: Double, name: String){
        extrasPrice = price
        sharedViewModel.addExtras(name)
    }

    fun submitOrder(){
        sharedViewModel.updatePrice(extrasPrice)

    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_orderDetailsAddExtras_to_menu)
    }
}