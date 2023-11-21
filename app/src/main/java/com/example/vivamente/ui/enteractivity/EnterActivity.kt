package com.example.vivamente.ui.enteractivity

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.content.ContextCompat
import com.example.vivamente.connectionchecker.ConnectionChecker
import com.example.vivamente.databinding.ActivityEnterBinding
import com.example.vivamente.ui.loginorregisteractivity.LoginOrRegisterActivity

class EnterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnterBinding
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        timer = object : CountDownTimer(3000, 100){

            override fun onTick(remaining: Long) {
                    if (remaining <=  1700L){
                        binding.loadingText.text = "Verificando conexão com a internet..."
                    }
            }

            override fun onFinish() {
               if (networkChecker.hasInternetConnection()){
                   startActivity(Intent(this@EnterActivity, LoginOrRegisterActivity::class.java))
               }else{
                   binding.loadingText.text = "Nenhuma conexão encontrada\nVerifique sua conexão com internet"
               }
            }

        }

        timer.start()

    }

    override fun onStop() {
        super.onStop()

        timer.cancel()
    }

    private val networkChecker by lazy {
        ConnectionChecker(ContextCompat.getSystemService(this, ConnectivityManager::class.java))
    }
}

