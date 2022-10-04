package com.example.hmwrk17

import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomtest.databinding.FragmentSwipeBinding


class SwipeFragment : BaseFragment<FragmentSwipeBinding>(FragmentSwipeBinding::inflate) {
    private val categories = mutableListOf<SwipeItems>()
    private val myAdapter: SwipeLayoutAdapter by lazy { SwipeLayoutAdapter() }

    override fun viewCreated() {

        binding.rvCategories.isLayoutFrozen = true
    }

    override fun listeners() {
        drawerListener()
        setupRecycler()
        populateList()
        select()
    }


    private fun drawerListener() {
        binding.drawer.setOnTouchListener(object : Swipe(requireContext()) {
            override fun onSwipeRight() {
                startDrawer()
            }

            override fun onSwipeLeft() {
                endDrawer()
            }
        }
        )
    }

    private fun select() {
        myAdapter.apply {
            setOnItemClickListener { item, _ ->
                Toast.makeText(
                    requireContext(),
                    "${item.rategoryName} selected",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun startDrawer() {
        binding.drawer.openDrawer(
            GravityCompat.START, true
        )

    }

    private fun endDrawer() {
        binding.drawer.closeDrawer(
            GravityCompat.END, true
        )
    }


    private fun populateList() {
        categories.add(
            SwipeItems(
                "Dashboard",
            )
        )

        categories.add(
            SwipeItems(
                "Messages",
            )
        )

        categories.add(
            SwipeItems(
                "Notifications",
            )
        )

        categories.add(
            SwipeItems(
                "Callendar",
            )
        )

        categories.add(
            SwipeItems(
                "Usage",
            )
        )

        categories.add(
            SwipeItems(
                "Settings",
            )
        )


    }

    private fun setupRecycler() {
        binding.rvCategories.apply {
            adapter = myAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false,
                )
        }
        myAdapter.submitList(categories)
    }

}