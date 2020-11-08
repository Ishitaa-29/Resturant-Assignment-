package com.example.liverest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), LifecycleObserver {

    companion object{

        class restViewModel: ViewModel(){
            private val _restList = MutableLiveData<MutableList<rest>>()
            fun restList() = _restList
            fun addRest(it:rest){
                var temp = _restList.value?: mutableListOf()
                temp.add(it)
                _restList.value = temp

            }
        }

    }

    private lateinit var mainRecycler: RecyclerView
    private lateinit var myViewModel: restViewModel
    private val dummyData = mutableListOf<rest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecycler = findViewById(R.id.main_recycler_view)

        myViewModel = ViewModelProvider(this).get(restViewModel::class.java)

        myViewModel.restList().observe(this, {
            attachAdapter(it)
        })

        attachAdapter(myViewModel.restList().value?: mutableListOf())

        addDummyData()

    }

    private fun addDummyData() {
        myViewModel.addRest(rest("KFC", "Delhi", 4.5, "Strips bucket"))
        myViewModel.addRest(rest("Subway", "Delhi", 4.0, "Paneer Tikka 6'inch"))
        myViewModel.addRest(rest("Dominos", "Delhi", 3.5, "Paneer Makhani"))
        myViewModel.addRest(rest("Wat-A-Burger", "Delhi", 5.0, "Punjab express"))
        myViewModel.addRest(rest("Food Overloaded", "Delhi", 2.5, "Cheesy fries"))
    }

    fun attachAdapter(it: MutableList<rest>){
        mainRecycler.setHasFixedSize(true)
        val myLayoutManager = LinearLayoutManager(this)
        myLayoutManager.orientation = RecyclerView.VERTICAL
        mainRecycler.layoutManager = myLayoutManager
        mainRecycler.adapter = adapter(it)
    }
}