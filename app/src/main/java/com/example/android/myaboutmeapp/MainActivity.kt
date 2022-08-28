package com.example.android.myaboutmeapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.android.myaboutmeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Note in Activities you don't inflate the layout you just setting them
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Make instance of User and connect it with binding
        user = User()
        binding.user = user

        // Setting onClickLister for the button
        binding.doneButton.setOnClickListener { showUserInformation(it) }
    }

    private fun showUserInformation(view: View){
        // Check if user name is empty
        if(binding.userNameEditText.text.isEmpty()){
            Toast.makeText(this, "Please Enter Your Name!", Toast.LENGTH_SHORT).show()
            return
        }
        // Getting user first and last name
        val userName = binding.userNameEditText.text.toString()
        // Get user first & last name separate
        val indexOfSeparation = userName.indexOf(" ")
        // In case user entered only first name without space after it
        if(indexOfSeparation == -1){
            user.firstName = userName
        }
        else{
            user.firstName = userName.slice(0 until indexOfSeparation)
            user.lastName = userName.slice(indexOfSeparation+1 until userName.length)
        }

        binding.apply{
            invalidateAll()
            // Hiding EditText, Button
            userNameEditText.visibility = View.GONE
            doneButton.visibility = View.GONE
            // Show UserName and Bio Text
            showUserNameText.visibility = View.VISIBLE
            bioText.visibility = View.VISIBLE
        }
        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}