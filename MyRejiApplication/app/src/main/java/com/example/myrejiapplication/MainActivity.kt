package com.example.myrejiapplication


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myrejiapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.settingMenu.setOnClickListener {
            //遷移先のactivityを開く
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
    }
}
