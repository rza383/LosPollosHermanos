package com.example.lospolloshermanos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lospolloshermanos.databinding.FragmentCompleteOrderBinding
import com.example.lospolloshermanos.model.OrderViewModel


class CompleteOrder : Fragment() {

    private var binding: FragmentCompleteOrderBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentCompleteOrderBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            completeOrderFragment = this@CompleteOrder
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun goToNextScreen(){
        sharedViewModel.setUserName(binding?.nameInputEditName?.text.toString())
        sharedViewModel.setUserAddress(binding?.addressInputEditAddress?.text.toString())
        findNavController().navigate(R.id.action_completeOrder_to_summaryFragment)
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_completeOrder_to_menu)
    }
}