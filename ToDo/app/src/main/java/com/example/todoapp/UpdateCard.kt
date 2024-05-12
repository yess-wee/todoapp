package com.example.todoapp


//private lateinit var binding: ActivityCreateCardBinding
//  binding = ActivityCreateCardBinding.inflate(layoutInflater)
//        setContentView(binding.root)






import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.todoapp.databinding.ActivityCreateCardBinding
import com.example.todoapp.databinding.ActivityUpdateCardBinding  // Import the generated binding class
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase

    private lateinit var binding: ActivityUpdateCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)

        binding = ActivityUpdateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            binding.createTitle.setText(title)
            binding.createPriority.setText(priority)


            binding.deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            binding.createTitle.text.toString(),
                            binding.createPriority.text.toString()
                        )
                    )
                }
                myIntent()
            }

            binding.updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    binding.createTitle.text.toString(),
                    binding.createTitle.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1, binding.createTitle.text.toString(),
                            binding.createPriority.text.toString()
                        )
                    )
                }
                myIntent()
            }

        }
    }

    fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}