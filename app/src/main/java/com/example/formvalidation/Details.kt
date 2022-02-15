package com.example.formvalidation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.formvalidation.databinding.ActivityDetailsBinding
import com.example.formvalidation.databinding.ActivityMainBinding

class Details : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("Email")
        val phone = intent.getStringExtra("Phone")
        val city = intent.getStringExtra("City")
        val univ = intent.getStringExtra("University")
        val course = intent.getStringExtra("Course")
        val passYR = intent.getStringExtra("Year of Passing")

        binding.displayEmail.text=email
        binding.displayPhone.text=phone
        binding.displayCity.text=city
        binding.displayUniversity.text=univ
        binding.displayCourse.text=course
        binding.displayPassing.text=passYR



    }
}