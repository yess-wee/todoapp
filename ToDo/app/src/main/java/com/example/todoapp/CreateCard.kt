package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityCreateCardBinding  // Ensure the import matches your generated binding class name

class CreateCard : AppCompatActivity() {
    // Property for the binding object
    private lateinit var binding: ActivityCreateCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the binding object
        binding = ActivityCreateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val title = binding.createTitle.text.toString().trim()
            val priority = binding.createPriority.text.toString().trim()
            if (title.isNotEmpty() && priority.isNotEmpty()) {
                DataObject.setData(title, priority)

                // Commented out database operation for simplicity, uncomment and modify if needed
                // GlobalScope.launch {
                //     database.dao().insertTask(Entity(0, title, priority))
                // }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
