package com.example.shop3
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private  lateinit var  auth: FirebaseAuth


    var TAG = "WillTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //創建資料到database

        val db = FirebaseFirestore.getInstance()

        auth = FirebaseAuth.getInstance()


        val user = hashMapOf(

            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815,
            "email" to "darthhun455@gmail.com"
        )

        db.collection("users")
            .document("darthhun455@gmail.com")
            .set(user)
            .addOnSuccessListener { documentReference ->
                Log.d("MainActivity", "DocumentSnapshot added with ID: ${documentReference}")
            }
            .addOnFailureListener { e ->
                Log.w("MainActivity", "Error adding document", e)
            }

        //創建資料到database





    }

    //發文

    private fun articlePost(
        content: String,
        id: String,
        tag: String,
        title: String,
        author: String,
        time: String
    ) {
        val db = FirebaseFirestore.getInstance()
        val docID = db.collection("Articles").document().id
        val user = hashMapOf(
            "article_content" to content,
            "article_id" to docID,
            "article_tag" to tag,
            "article_title" to title,
            "author" to author,
            "create_time" to time
        )
      // Add a new document with a generated ID

        db.collection("Articles")
            .document(docID)
            .set(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with content_POST:$content")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document_POST", e)
            }
    }


    //發文



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }






}
