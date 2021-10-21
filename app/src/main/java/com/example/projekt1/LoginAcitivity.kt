package com.example.projekt1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login_acitivity.*

class LoginAcitivity : AppCompatActivity() {
    private lateinit var db: FirebaseDatabase
   private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_acitivity)
        auth = FirebaseAuth.getInstance()


        bt_register.setOnClickListener{
            auth.createUserWithEmailAndPassword(et_mail.text.toString(), et_password.text.toString())
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this, "Rejestracja się powiodła", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this, "Rejestracja się nie powiodła", Toast.LENGTH_LONG).show()
                    }
                }
        }

        bt_login.setOnClickListener {
            auth.signInWithEmailAndPassword(et_mail.text.toString(), et_password.text.toString())
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this, "Logowanie się powiodło", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    else{
                        Toast.makeText(this, "Logowanie się nie powiodło", Toast.LENGTH_LONG).show()
                    }
                }
        }
//        db = FirebaseDatabase.getInstance()
//        val user = auth.currentUser
//        dbOperations(db, user)
    }

//    fun dbOperations(db: FirebaseDatabase, user: FirebaseUser?){
//        if(user != null) {
//            val ref = db.getReference("users/"+user.uid)
//            Log.e(
//                "XXXXXXXXXXXXXXXXDDDDDDDDDDDDDDDDDDDDDDD",
//                "XXXXXXXXXXXXXXXXXDDDDDDDDDDDDDDDDDDDDDDDDDD"
//            )
////            val ref = db.getReference("objects")
//            val string_ref = ref.child("strings")
//            val shm = hashMapOf<String, String>()
//            shm["string1"] = "asdasdasd"
//            shm["string2"] = "134k1cmkd"
//            string_ref.setValue(shm)
//
//        }
//    }


}