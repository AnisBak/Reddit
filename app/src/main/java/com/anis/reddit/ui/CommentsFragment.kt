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
import androidx.recyclerview.widget.LinearLayoutManager
import com.anis.reddit.databinding.FragmentCommentsBinding
import com.anis.reddit.viewmodels.RedditViewModel
import kotlinx.coroutines.launch

class CommentsFragment: Fragment() {
    private val redditViewModel: RedditViewModel by activityViewModels()
    private var _binding: FragmentCommentsBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentCommentsBinding.inflate(inflater, container, false)
        binding.comments.layoutManager = LinearLayoutManager(context)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                redditViewModel.comments.collect { items ->
                    binding.comments.adapter = RedditCommentAdapter(items)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}