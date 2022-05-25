package com.example.applesugar.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.request.Options
import coil.size.Scale
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.example.applesugar.databinding.FragmentProfileBinding
import okio.buffer
import okio.source

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sp = requireActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val avatarUrl = sp.getString("avatarUrl", "")
        binding.ivProfilePic.loadSvg(avatarUrl!!)
        binding.tvUsername.text = sp.getString("userName", "User")

    }

    private fun ImageView.loadSvg(url: String) {

        val imageLoader = ImageLoader.Builder(requireContext())
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .data(url)
            .target(this)
            .transformations(CircleCropTransformation())
            .build()

        imageLoader.enqueue(request)
    }
}