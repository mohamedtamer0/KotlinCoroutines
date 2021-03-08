package com.example.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var myTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myTextView = findViewById(R.id.my_text)


        val parentJop = Job()
        val job: Job = GlobalScope.launch(parentJop) {
            launch { getUserFromNetwork() }
            launch { getUserFromDatabase() }
        }

        job.cancel()

//        doTaskA(1)
//        printMyTextAfterDelay1("Hello Kotlin")

//        GlobalScope.launch (newSingleThreadContext("Tamer Thread")) {
//            Log.d("Fun", "Current thread : ${Thread.currentThread().name}")
//            printMyTextAfterDelay2("Coroutines")
//        }

    }


    //Coroutines
    suspend fun printCoroutines(myText: String) {
        withContext(Dispatchers.IO) {
            delay(2000)
            Log.d("MainActivity", myText)
        }

    }


    private suspend fun getUserFromNetwork() : String {
        delay(2000)
        return "Tamer"
    }
    private suspend fun getUserFromDatabase() : String {
        delay(3000)
        return "Tamer"
    }






//    fun printMyTextAfterDelay1(myText: String) {
//        Thread.sleep(2000)
//        Log.d("my Fun", myText)
//    }
//
//
//    private fun doTaskA(label: Int) {
//        when (label) {
//            1 -> {
//                Log.d("task A", "we deal with one A")
//                doTaskB(1)
//            }
//            2 -> {
//                Log.d("task A", "we deal with two A")
//                doTaskB(2)
//            }
//            3 -> {
//                Log.d("task A", "we deal with three A")
//                doTaskB(3)
//            }
//        }
//    }
//
//    private fun doTaskB(label: Int) {
//        when (label) {
//            1 -> {
//                Log.d("task B", "we deal with one B")
//                doTaskA(2)
//            }
//            2 -> {
//                Log.d("task B", "we deal with two B")
//                doTaskA(3)
//            }
//            3 -> {
//                Log.d("task B", "we deal with three B")
//            }
//        }
//    }


}