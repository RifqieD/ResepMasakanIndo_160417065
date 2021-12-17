package com.example.resepmasakanindo_160417065

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.resepmasakanindo_160417065.databinding.FragmentHomeBinding
import com.example.resepmasakanindo_160417065.view.ResepListAdapter
import com.example.resepmasakanindo_160417065.viewmodel.ResepViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var resepViewModel: ResepViewModel
    private lateinit var adapter: ResepListAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        resepViewModel = ViewModelProvider(this).get(ResepViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewmodel = resepViewModel

        resepViewModel.loadResep()

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.tambahResepFragment)
        }

        adapter = ResepListAdapter(arrayListOf())
        layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        observeViewModel()
    }

    private fun observeViewModel() {
        resepViewModel.resepLD.observe(viewLifecycleOwner, {
            adapter.updateList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}