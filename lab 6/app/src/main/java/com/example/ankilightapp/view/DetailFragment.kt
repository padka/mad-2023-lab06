package com.example.ankilightapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.ankilightapp.databinding.FragmentDetailBinding
import com.example.ankilightapp.viewmodel.CardDetailViewModel

class DetailFragment : Fragment() {

    private val viewModel: CardDetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardId = args.cardId
        viewModel.loadCard(cardId)

        viewModel.card.observe(viewLifecycleOwner) { card ->
            binding.wordTextView.text = card.word
            binding.translationTextView.text = card.translation
            binding.exampleTextView.text = card.example
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
