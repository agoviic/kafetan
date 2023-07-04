package com.example.calsdown_projekatzakol.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mvvmappclass.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            if (binding.tvUsername.text.toString().length<5 ){
                Toast.makeText(this, "Username mora sadrzati minimum 5 karaktera", Toast.LENGTH_SHORT).show()
            }else if(binding.tvPassword.text.toString().length<8){
                Toast.makeText(this, "Password mora sadrzati minimum 8 karaktera", Toast.LENGTH_SHORT).show()
            }else{

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Username", binding.tvUsername.text.toString())
                startActivity(intent)
            }
        }
    }
}