package com.study.ppjoke.ui.publish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.study.libnavannotation.ActivityDestination
import com.study.ppjoke.databinding.ActivityPublishBinding

@ActivityDestination(pageUrl = "main/tabs/publish", asStarter = false)
class PublishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPublishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPublishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textDashboard.text = "This is Publish"

    }
}