package com.example.lospolloshermanos.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel: ViewModel() {

    private val _mainDish = MutableLiveData<MutableList<String>>()
    val mainDish: LiveData<MutableList<String>> = _mainDish

    private val _extras = MutableLiveData<MutableList<String>>()
    val extras: LiveData<MutableList<String>> = _extras

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private var _name = MutableLiveData<String>()
    val name:LiveData<String> = _name

    private var _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    var dateOptions: MutableList<String> = getPickupOptions()

    private var _bufferDishList = mutableListOf<String>()
    private var _bufferExtrasList = mutableListOf<String>()

    init{
        resetOrder()
    }

    fun updatePrice(price: Double,) {
        _price.value = _price.value?.plus(price)
    }

    fun setDate(date: String){
        _date.value = date
    }

    fun addExtras(extra: String){
        _bufferExtrasList.add(extra)
        _extras.postValue(_bufferExtrasList)
        Log.d("ViewModel", "${extras.value}")
    }

    fun addMainDish(dish: String){
        _bufferDishList.add(dish)
        _mainDish.postValue(_bufferDishList)
        Log.d("ViewModel", "${mainDish.value}")
    }

    fun setUserName(name: String){
        _name.value = name
        Log.d("ViewModel", "$name")
    }

    fun setUserAddress(address: String){
        _address.value = address
        Log.d("ViewModel", "$address")
    }

    private fun getPickupOptions(): MutableList<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(2) {
            calendar.add(Calendar.DATE, 1)
            options.add(formatter.format(calendar.time))
        }
        return options
    }

    fun resetOrder(){
        _price.value = 0.0
        _address.value = ""
        _name.value = ""
        _mainDish.value?.clear()
        _extras.value?.clear()
        _date.value = dateOptions[0]
    }


}


