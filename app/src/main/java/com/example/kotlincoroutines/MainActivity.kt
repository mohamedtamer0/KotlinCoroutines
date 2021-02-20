package com.example.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doTaskA(1)
    }


    private fun doTaskA(label:Int) {
        when(label) {
            1 -> {
                Log.d("task A","we deal with one A")
                doTaskB(1)
            }
            2 -> {
                Log.d("task A","we deal with two A")
                doTaskB(2)
            }
            3 -> {
                Log.d("task A","we deal with three A")
                doTaskB(3)
            }
        }
    }

    private fun doTaskB(label:Int) {
        when(label) {
            1 -> {
                Log.d("task B","we deal with one B")
                doTaskA(2)
            }
            2 -> {
                Log.d("task B","we deal with two B")
                doTaskA(3)
            }
            3 -> {
                Log.d("task B","we deal with three B")
            }
        }
    }




}