package com.example.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {
    private val parentJop = Job()
    lateinit var myTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myTextView = findViewById(R.id.my_text)
        val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + parentJop)
        coroutineScope.launch {

        }

        val job: Job = GlobalScope.launch(parentJop) {
            val child1 = launch { getUserFromNetwork() }
            val child2 = launch { getUserFromDatabase() }

            //joinAll(child1, child2)
            //launch { delay(2000) }
        }

        val kotlinChannel = Channel<String>()
        val charList = arrayOf("A", "B", "C", "D")

        runBlocking {
            launch {
                for (char in charList) {
                    kotlinChannel.send(char)
                    delay(1000)
                }
            }

            launch {
                for (char in kotlinChannel) {
                    Log.d("Here", char)
                }
            }


        }



        //////////////// Flow \\\\\\\\\\\\\\\\\\\\

        runBlocking {
            //producer
            flow<Int> {
                for (i in 1..10) {
                    emit(i)
                    Log.d("here producer", i.toString())
                }
            }.filter { i:Int -> i < 5 } //Intermediate
                    .collect {
                        Log.d("here collector", it.toString())
                    }
        }





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


    private suspend fun getUserFromNetwork(): String {
        delay(2000)
        return "Tamer"
    }

    private suspend fun getUserFromDatabase(): String {
        delay(3000)
        return "Tamer"
    }

    override fun onStop() {
        super.onStop()
        parentJop.cancel()
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