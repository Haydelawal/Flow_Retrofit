package com.example.flow_retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flow_retrofit.databinding.FragmentThirdBinding
import com.example.flow_retrofit.viewmodel.MyCustomPostVM
import com.example.flow_retrofit.viewmodel.MyPathViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class ThirdFragment : Fragment() {

    private lateinit var _binding: FragmentThirdBinding
    private val binding get() = _binding!!

    private val myViewModel: MyCustomPostVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            val myNo = binding.editTextNumber.text
            myViewModel.getPostFlow(myNo.toString().toInt())

            lifecycleScope.launchWhenCreated {
                if (myViewModel.getPost(myNo.toString().toInt()).isSuccessful) {
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
                if (myViewModel.getPost(myNo2).isSuccessful) {
                    binding.textView2.text =
                        "${myViewModel.getPost(myNo2).body()?.get(0)?.body}, ${myViewModel.getPost(myNo2).body()?.get(2)?.body}"

                } else {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.button4.setOnClickListener {
            val action = ThirdFragmentDirections.actionThirdFragmentToFourthFragment()
            findNavController().navigate(action)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}