package com.example.lospolloshermanos

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lospolloshermanos.databinding.FragmentSummaryBinding
import com.example.lospolloshermanos.model.OrderViewModel
import java.net.URLEncoder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SummaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SummaryFragment : Fragment() {

    private var binding: FragmentSummaryBinding? = null
    private val sharedVieModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedVieModel
            lifecycleOwner = viewLifecycleOwner
            fragmentSummary = this@SummaryFragment
            mainDish = sharedVieModel.mainDish.value?.joinToString(", ")
            extras = sharedVieModel.extras.value?.joinToString(", ") { it.lowercase() }
            Log.d("summary", "${mainDish}")
            Log.d("summary", "$extras")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun cancelOrder(){
        sharedVieModel.resetOrder()
        findNavController().navigate(R.id.action_summaryFragment_to_menuFragment)
    }

    fun sendToWPP(){

        val orderSummary = getString(
            R.string.order_details,
            sharedVieModel.name.value.toString(),
            sharedVieModel.mainDish.value?.joinToString(", "),
            sharedVieModel.extras.value?.joinToString(", ") { it.lowercase() },
            sharedVieModel.date.value.toString(),
            sharedVieModel.price.value.toString()
        )
        try{
            val number = "+77010527036"
            val url = "https://api.whatsapp.com/send?phone=$number&text=${URLEncoder.encode(orderSummary, "UTF-8")}";
            val intent = Intent(Intent.ACTION_VIEW).apply {
                //type = "text/plain"
                Log.d("intent", "$orderSummary")
                data = Uri.parse(url)
                setPackage("com.whatsapp")
            }
            startActivity(intent)
            } catch (e: PackageManager.NameNotFoundException ) {
            Toast.makeText(context, "Whatsapp app not installed in your phone",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        }

    }
