package com.example.formvalidation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import com.example.formvalidation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailListener()
        passwordListener()
        phoneFocusListener()

        binding.submitButton.setOnClickListener{
            submitForm()
        }
    }

    private fun submitForm()
    {

        var pass= binding.passwordEditText.text.toString()
        var cpass=binding.confirmpasswordEditText.text.toString()

        binding.emailContainer.helperText = validEmail()
        binding.passwordContainer.helperText = validPassword()
        binding.phoneContainer.helperText = validPhone()

        val validEmail = binding.emailContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null
        val validPhone = binding.phoneContainer.helperText == null

        if (validEmail && validPassword && validPhone &&(pass==cpass)){
            sendData()

        }
        else{
            invalidForm()
        }

    }

    private fun sendData() {
        val intent = Intent(this@MainActivity,Details::class.java)
        intent.putExtra("Email", binding.emailEditText.text.toString())
        intent.putExtra("Phone", binding.phoneEditText.text.toString())
        intent.putExtra("City", binding.cityEditText.text.toString())
        intent.putExtra("University", binding.universityEditText.text.toString())
        intent.putExtra("Course",binding.courseEditText.text.toString())
        intent.putExtra("Year of Passing",binding.passingEditText.text.toString())
        startActivity(intent)
        this.finish()
    }

    private fun invalidForm()
    {
        var pass= binding.passwordEditText.text.toString()
        var cpass=binding.confirmpasswordEditText.text.toString()


        var message = ""
        if(binding.emailContainer.helperText != null)
            message += "\n\nEmail: " + binding.emailContainer.helperText
        if(binding.passwordContainer.helperText != null)
            message += "\n\nPassword: " + binding.passwordContainer.helperText
        if(binding.phoneContainer.helperText != null)
            message += "\n\nPhone: " + binding.phoneContainer.helperText
        if(pass!=cpass)
            message += "\n\nConfirm Password: Passwords do not match."

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->

            }
            .show()
    }



    //Password
    private fun passwordListener() {
        binding.passingEditText.setOnFocusChangeListener{_,focused->
            if (!focused)
            {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String?
        {
            val passwordText = binding.passwordEditText.text.toString()
            if(passwordText.length < 8)
            {
                return "Minimum 8 Character Password"
            }
            if(!passwordText.matches(".*[A-Z].*".toRegex()))
            {
                return "Must Contain 1 Upper-case Character"
            }
            if(!passwordText.matches(".*[a-z].*".toRegex()))
            {
                return "Must Contain 1 Lower-case Character"
            }
            if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
            {
                return "Must Contain 1 Special Character (@#\$%^&+=)"
            }

            return null
        }


    //Email
    private fun emailListener() {
        binding.emailEditText.setOnFocusChangeListener{_,focused->
            if (!focused)
            {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.emailEditText.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email Address"
            }
        return null


    }

    //Phone
    private fun phoneFocusListener()
    {
        binding.phoneEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.phoneContainer.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String?
    {
        val phoneText = binding.phoneEditText.text.toString()
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 10)
        {
            return "Must be 10 Digits"
        }
        return null
    }
}