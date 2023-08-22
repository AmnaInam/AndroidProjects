package com.example.androidstructure.presentation.modules.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidstructure.R
import com.example.androidstructure.databinding.FragmentSplashBinding
import com.example.androidstructure.presentation.modules.splash.SplashViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var viewModel : SplashViewModel

    private lateinit var splashBinding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashBinding = FragmentSplashBinding.bind(view)
        splashBinding.btn.setOnClickListener {
            viewModel.getLocations()
            viewModel.successful.observe(viewLifecycleOwner){ successful ->
                 if (successful == true){
                    Toast.makeText(requireContext(),"Hurrian",Toast.LENGTH_SHORT).show()
                 }else if(successful == false){
                    Toast.makeText(requireContext(),"Failed",Toast.LENGTH_SHORT).show()
                 }
            }
        }
    }
}