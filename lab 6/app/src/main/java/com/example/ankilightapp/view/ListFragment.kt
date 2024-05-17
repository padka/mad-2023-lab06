package com.example.ankilightapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ankilightapp.databinding.FragmentListBinding
import com.example.ankilightapp.view.adapter.CardAdapter
import com.example.ankilightapp.viewmodel.CardListViewModel

class ListFragment : Fragment() {

    private val viewModel: CardListViewModel by viewModels()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CardAdapter(
            onClick = { cardId ->
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(cardId)
                findNavController().navigate(action)
            },
            onDelete = { card ->
                viewModel.deleteCard(card)
            }
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.cards.observe(viewLifecycleOwner) { cards ->
            adapter.submitList(cards)
        }

        binding.fab.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToEditFragment(0L)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
