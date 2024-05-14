package com.jecr.recyclerview_r

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth
import com.jecr.recyclerview_r.databinding.ActivitySingInBinding

class SingInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingInBinding
    private lateinit var firebaseauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivitySingInBinding.inflate(layoutInflater)

        setContentView(binding.root)
        firebaseauth = FirebaseAuth.getInstance()

        // Sino tiene cuenta ir a crear una en singUP
        binding.tvGoToSingUp.setOnClickListener{
            val intent = Intent(this, SingUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnSingIn.setOnClickListener {
            val email = binding.teEmail.text.toString()
            val pass = binding.tePass.text.toString()

            if (email.isEmpty() or pass.isEmpty()){
                Toast.makeText(this,"Verifica la entrada",Toast.LENGTH_SHORT).show()
            }else{
               firebaseauth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                   if(it.isSuccessful){
                       // Redirigir a mainActivity }(pagina princial)
                       val intent = Intent(this, MainActivity::class.java)
                       startActivity(intent)
                   } else {
                       Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                   }
               }


            }

        }

    }

    override fun onStop() {
        super.onStop()
        firebaseauth.signOut()
    }
}