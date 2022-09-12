package com.anis.reddit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anis.reddit.databinding.FragmentPostsBinding


import kotlinx.coroutines.launch



class PostsFragment : Fragment() {
    private lateinit var redditAdapter : RedditPostAdapter
    private var _binding: FragmentPostsBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val redditViewModel: RedditViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentPostsBinding.inflate(inflater, container, false)
        binding.posts.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                redditViewModel.posts.collect {
                    redditAdapter = RedditPostAdapter{ commentUrl ->
                        redditViewModel.fetchComments(commentUrl)
                        findNavController().navigate(
                            PostsFragmentDirections.actionRedditPostsFragmentToCommentsFragment()
                        )
                    }
                    binding.posts.adapter = redditAdapter
                    binding.posts.adapter = redditAdapter.withLoadStateFooter(
                        footer = RedditLoadingAdapter()
                    )
                    redditAdapter.submitData(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
