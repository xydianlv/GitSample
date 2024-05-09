package com.example.gitsample.function.applayout.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitsample.databinding.FragmentTestBinding

class FragmentTest : Fragment() {

    companion object {

        @JvmStatic
        fun fragment(): Fragment {
            return FragmentTest()
        }
    }

    private lateinit var binding: FragmentTestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = ListAdapter()
    }
}