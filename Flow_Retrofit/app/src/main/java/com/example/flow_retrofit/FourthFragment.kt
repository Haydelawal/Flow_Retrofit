package com.example.flow_retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flow_retrofit.databinding.FragmentFourthBinding
import com.example.flow_retrofit.viewmodel.MyCustomPostVM
import com.example.flow_retrofit.viewmodel.MyCustomPostVM2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FourthFragment : Fragment() {

    private lateinit var _binding: FragmentFourthBinding
    private val binding get() = _binding!!

    private val myViewModel: MyCustomPostVM2 by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFourthBinding.inflate(inflater, container, false)

        val options : HashMap<String, String> = HashMap()

        options["_sort"] = "id"
        options["_order"] = "desc"

        binding.button.setOnClickListener {
            val myNo = binding.editTextNumber.text
            myViewModel.getPostFlow(myNo.toString().toInt(), options)

            lifecycleScope.launchWhenCreated {
                if (myViewModel.getPost(myNo.toString().toInt(), options).isSuccessful) {
                    myViewModel.myResponseFlow.collectLatest {
                        binding.textView.text = it
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()

                }
            }
        }

        binding.button2.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                val myNo2 = binding.editTextNumber2.text.toString().toInt()
                if (myViewModel.getPost(myNo2, options).isSuccessful) {
                    binding.textView2.text =
                        "${myViewModel.getPost(myNo2, options).body()?.get(0)?.body}, ${myViewModel.getPost(myNo2, options).body()?.get(2)?.body}"

                } else {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}