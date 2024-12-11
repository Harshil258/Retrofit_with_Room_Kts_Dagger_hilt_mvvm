package com.harshil.retrofitwithroomkts

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.harshil.retrofitwithroomkts.Adapter.PostAdapter
import com.harshil.retrofitwithroomkts.Model.Post
import com.harshil.retrofitwithroomkts.ViewModel.PostViewModel
import com.harshil.retrofitwithroomkts.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val postViewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        initRecyclerView()
        postViewModel.getData.observe(this, Observer { response->
            postViewModel.insert(response)
        })
        postViewModel.getAllPost.observe(this, Observer { response->
            binding.recyclerview.visibility= View.VISIBLE
            binding.progressBar.visibility= View.GONE
            postAdapter.submitList(response)

        })


    }

    private fun initRecyclerView() {
        postAdapter= PostAdapter()
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=postAdapter
        }
    }
}