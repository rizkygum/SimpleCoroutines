package com.gumilang.simplecoroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Thread", Thread.currentThread().name)

        GlobalScope.launch(context = Dispatchers.IO) {
            Log.d("Thread", Thread.currentThread().name)

            val imageUrl = URL("https://www.gstatic.com/devrel-devsite/prod/v425077d6c7be97246d05a953898cb9591a173a3cef753a451b8729896196bc0a/firebase/images/lockup.png?dcb_=0.15367641908869145")
            val connection : HttpURLConnection  = imageUrl.openConnection() as  HttpURLConnection
            connection.doInput = true
            connection.connect()

            val inputStream: InputStream = connection.inputStream
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)

            launch(Dispatchers.Main){
                Log.d("Thread", Thread.currentThread().name)
                imageView.setImageBitmap(bitmap)
            }
        }
    }
}