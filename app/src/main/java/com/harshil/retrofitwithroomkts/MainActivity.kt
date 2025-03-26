package com.harshil.retrofitwithroomkts

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.harshil.retrofitwithroomkts.Adapter.PostAdapter
import com.harshil.retrofitwithroomkts.Model.PostModel
import com.harshil.retrofitwithroomkts.ViewModel.PostViewModel
import com.harshil.retrofitwithroomkts.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        setupWindowInsets()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter()
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getData.observe(this@MainActivity) { response ->
                        viewModel.insert(response)
                    }
                }

                launch {
                    viewModel.getAllPostModel.observe(this@MainActivity) { posts ->
                        binding.progressBar.visibility =
                            if (posts.isEmpty()) View.VISIBLE else View.GONE
                        binding.recyclerview.visibility =
                            if (posts.isNotEmpty()) View.VISIBLE else View.GONE

                        val itemsWithTitles = mutableListOf<PostModel>()
                        posts.forEachIndexed { index, post ->
                            if (index % 5 == 0) {
                                itemsWithTitles.add(
                                    PostModel(
                                        body = ("TITLE ${((index / 5) + 1)}").toString()
                                    )
                                )
                            }
                            itemsWithTitles.add(post)
                        }
                        postAdapter.submitList(itemsWithTitles)
                    }
                }
            }
        }
    }
}