package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityUpdateCardBinding  // Import the generated binding class

class UpdateCard : AppCompatActivity() {
    // Declare the binding object
    private lateinit var binding: ActivityUpdateCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the binding object
        binding = ActivityUpdateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)  // Set the content view to the root of the binding object

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val cardInfo = DataObject.getData(pos)
            binding.createTitle.setText(cardInfo.title)
            binding.createPriority.setText(cardInfo.priority)

            binding.deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                // Consider replacing GlobalScope with a more structured concurrency approach
                // GlobalScope.launch {
                //     database.dao().deleteTask(Entity(pos, binding.createTitle.text.toString(), binding.createPriority.text.toString()))
                // }
                myIntent()
            }

            binding.updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    binding.createTitle.text.toString(),
                    binding.createPriority.text.toString()
                )
                // Consider replacing GlobalScope with a more structured concurrency approach
                // GlobalScope.launch {
                     DataObject.updateData(pos, binding.createTitle.text.toString(), binding.createPriority.text.toString())
                // }
                myIntent()
            }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
