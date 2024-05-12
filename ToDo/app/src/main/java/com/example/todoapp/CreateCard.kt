package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.todoapp.databinding.ActivityCreateCardBinding  // Ensure the import matches your generated binding class name
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CreateCard : AppCompatActivity() {

    private lateinit var binding: ActivityCreateCardBinding

    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        binding = ActivityCreateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        binding.saveButton.setOnClickListener {
            if (binding.createTitle.text.toString().trim { it <= ' ' }.isNotEmpty()
                && binding.createPriority.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                var title = binding.createTitle.getText().toString()
                var priority = binding.createPriority.getText().toString()
                DataObject.setData(title, priority)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority))

                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}