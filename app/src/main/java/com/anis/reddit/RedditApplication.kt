package com.anis.reddit

import android.app.Application
import com.anis.reddit.repositories.RedditRepo

class RedditApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        RedditRepo.initializeRetrofit()
    }
}