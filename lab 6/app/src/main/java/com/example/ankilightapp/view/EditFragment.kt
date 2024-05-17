package com.example.ankilightapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ankilightapp.databinding.FragmentEditBinding
import com.example.ankilightapp.viewmodel.CardEditViewModel

class EditFragment : Fragment() {

    private val viewModel: CardEditViewModel by viewModels()
    private val args: EditFragmentArgs by navArgs()
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardId = args.cardId
        if (cardId != 0L) {
            viewModel.loadCard(cardId)
        }

        binding.saveButton.setOnClickListener {
            val word = binding.wordEditText.text.toString()
            val translation = binding.translationEditText.text.toString()
            val example = binding.exampleEditText.text.toString()
            viewModel.saveCard(cardId, word, translation, example)
            findNavController().popBackStack()
        }

        viewModel.card.observe(viewLifecycleOwner) { card ->
            binding.wordEditText.setText(card.word)
            binding.translationEditText.setText(card.translation)
            binding.exampleEditText.setText(card.example)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
