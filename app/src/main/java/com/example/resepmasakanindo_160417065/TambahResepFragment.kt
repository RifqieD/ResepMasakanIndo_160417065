package com.example.resepmasakanindo_160417065

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.resepmasakanindo_160417065.databinding.FragmentTambahResepBinding
import com.example.resepmasakanindo_160417065.model.Resep
import com.example.resepmasakanindo_160417065.utils.observeOnce
import com.example.resepmasakanindo_160417065.viewmodel.KategoriViewModel
import com.example.resepmasakanindo_160417065.viewmodel.ResepViewModel
import com.example.resepmasakanindo_160417065.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import java.io.File

class TambahResepFragment : Fragment() {
    private var _binding: FragmentTambahResepBinding? = null
    private val binding get() = _binding!!

    private lateinit var resepViewModel: ResepViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var kategoriViewModel: KategoriViewModel
    private lateinit var imageUri: Uri
    private lateinit var resep: Resep

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                Picasso.get().load(imageUri).into(binding.imageView)
            }
        }

    private val pickPicture =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            Picasso.get().load(uri).into(binding.imageView)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTambahResepBinding.inflate(layoutInflater, container, false)
        resepViewModel = ViewModelProvider(this).get(ResepViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        kategoriViewModel = ViewModelProvider(this).get(KategoriViewModel::class.java)

        observeViewModelKategori()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kategoriViewModel.loadKategori()

        binding.imageView.setOnClickListener {
            openDialog()
        }

        binding.btnSimpan.setOnClickListener {
            val pos = binding.spinnerKategori.selectedItemPosition
            val kategoriId = kategoriViewModel.kategoriLD.value!![pos].kategoriId
            addResep(kategoriId)
            findNavController().navigateUp()
        }
    }

    private fun openDialog() {
        val items = arrayOf("Galeri", "Kamera")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Pilih gambar dari")
            .setItems(items) { dialog, which ->
                when (which) {
                    0 -> pickPicture.launch("images/jpg")
                    1 -> {
                        val photoFile = File.createTempFile(
                            "IMG_",
                            ".jpg",
                            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                        )

                        imageUri = FileProvider.getUriForFile(
                            requireContext(),
                            "${requireContext().packageName}.provider",
                            photoFile
                        )
                        takePicture.launch(imageUri)
                    }
                }
            }.show()
    }

    private fun addResep(kategoriId: Long) {
        resep = Resep(
            0,
            binding.inputTitle.text.toString(),
            binding.inputDeskripsi.text.toString(),
            imageUri.toString(),
            kategoriId,
            userViewModel.userLD.value!!.userId
        )

        resepViewModel.addResep(resep)
    }

    private fun observeViewModelKategori() {
        kategoriViewModel.kategoriLD.observeOnce(viewLifecycleOwner, {
            val listKategori = it.map { kategori -> kategori.nama }
            binding.spinnerKategori.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listKategori)
        })
    }
}