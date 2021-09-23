package com.dalvik.yugiohdex.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dalvik.yugiohdex.R
import com.dalvik.yugiohdex.databinding.FragmentGridBinding
import com.dalvik.yugiohdex.viewmodel.CardsViewmodel


class GridFragment : Fragment() {
    private lateinit var binding: FragmentGridBinding
    private lateinit var cardsViewmodel: CardsViewmodel
    private var viewBiding: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (viewBiding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_grid, container, false)
            cardsViewmodel = ViewModelProvider(requireActivity()).get(CardsViewmodel::class.java)
            viewBiding = binding.root
            binding.vm = cardsViewmodel
            binding.lifecycleOwner = this
            cardsViewmodel.getAllCards()
        }
        return viewBiding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
    }


    private fun setupNavigation() {
        registerForContextMenu(binding.imageFilter)
        binding.imageFilter.setOnClickListener {
            requireActivity().openContextMenu(it)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        requireActivity().menuInflater.inflate(R.menu.menu_filter, menu);
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        cardsViewmodel.filterCards(item.itemId)
        return  super.onOptionsItemSelected(item)
    }
}