package com.example.gymgenius

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var botao:Button = findViewById(R.id.btnLogin)
        var campoUser:EditText = findViewById(R.id.edUsername)
        var campoPassword:EditText = findViewById(R.id.edPassword)
        botao.setOnClickListener(View.OnClickListener { view ->
            var userName:String = campoUser.text.toString()
            var userPass:String = campoPassword.text.toString()
            if (userName == "gym" && userPass == "gym"){
                val intent = Intent(this, WorkoutsActivity::class.java)
                startActivity(intent)
            }
        })
    }
}