package com.example.resepmasakanindo_160417065

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.resepmasakanindo_160417065.databinding.FragmentLoginBinding
import com.example.resepmasakanindo_160417065.utils.observeOnce
import com.example.resepmasakanindo_160417065.viewmodel.UserViewModel


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        observeViewModel()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.isLoggedIn()
        binding.btnLogin.setOnClickListener {
            val username = binding.loginUsername.text
            val pass = binding.loginPass.text

            if (username.isNotEmpty() && pass.isNotEmpty()) {
                userViewModel.login(username.toString(), pass.toString())
                findNavController().navigate(
                    R.id.menuHome,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "Lengkapi username dan password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
    }

    private fun observeViewModel() {
        userViewModel.isLoggedInLD.observeOnce(viewLifecycleOwner, { isLoggedIn ->
            if (isLoggedIn) {
                findNavController().navigate(
                    R.id.menuHome,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                )
            }
        })
    }
}