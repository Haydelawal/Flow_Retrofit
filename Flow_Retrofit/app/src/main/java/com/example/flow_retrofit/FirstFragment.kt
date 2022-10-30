package com.example.flow_retrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.flow_retrofit.databinding.FragmentFirstBinding
import com.example.flow_retrofit.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var _binding: FragmentFirstBinding
    private val binding get() = _binding!!


    private val myViewModel: MyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        myViewModel.getPostFlow()
        lifecycleScope.launchWhenCreated {
            if (myViewModel.getPost().isSuccessful) {
                myViewModel.myResponseFlow.collectLatest {
                    binding.textView.text = it
                }
            } else {
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
            }

        }

        lifecycleScope.launchWhenCreated {
            if (myViewModel.getPost().isSuccessful) {
                binding.textView2.text =
                    "${myViewModel.getPost().body()?.id}, ${myViewModel.getPost().body()?.myUserId}"

            } else {
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}