package com.example.studentsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.adapter.StudentAdapter
import com.example.studentsapp.databinding.ActivityMainBinding
import com.example.studentsapp.helper.DataHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter // Declare studentAdapter as a member variable

    override fun onResume() {
        super.onResume()
        studentAdapter.getUpdate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bInsertStudent.setOnClickListener {
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }

        val dataHelper = DataHelper(this)
        Log.d("read", "read data")
        val studentList = dataHelper.getAllStudent()
        studentAdapter = StudentAdapter(this@MainActivity, studentList) // Instantiate studentAdapter

        binding.rvStudent.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = studentAdapter
        }
    }
}