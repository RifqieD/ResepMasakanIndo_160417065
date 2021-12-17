package com.example.resepmasakanindo_160417065

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.resepmasakanindo_160417065.databinding.FragmentRegisterBinding
import com.example.resepmasakanindo_160417065.utils.observeOnce
import com.example.resepmasakanindo_160417065.viewmodel.UserViewModel

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        binding.btnConfirm.setOnClickListener {
            val username = binding.username.text
            val pass = binding.pass.text
            val confirm = binding.konfirmasi.text

            if (username.isNotEmpty() || pass.isNotEmpty() || confirm.isNotEmpty()) {
                if (pass.toString() == confirm.toString()) {
                    userViewModel.register(username.toString(), pass.toString())
                } else {
                    Toast.makeText(requireContext(), "Password tidak cocok", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Lengkapi semua form", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        val userObserver = userViewModel.userLD
        userObserver.observeOnce(viewLifecycleOwner, {
            if (it != null) {
                findNavController().navigate(
                    R.id.menuHome,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                )
            }
        })
    }
}