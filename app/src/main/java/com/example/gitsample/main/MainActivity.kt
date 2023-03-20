package com.example.gitsample.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gitsample.databinding.ActivityMainBinding
import com.example.gitsample.function.ActivityFunctionList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initList()
    }

    private fun initList() {
        binding.mainList.addItem("FunctionList", "一些好玩的小功能") {
            ActivityFunctionList.open(this)
        }.refreshList()
    }
}