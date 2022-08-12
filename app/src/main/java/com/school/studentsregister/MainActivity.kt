package com.school.studentsregister

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.school.studentsregister.databinding.ActivityMainBinding
import com.school.studentsregister.db.Student
import com.school.studentsregister.db.StudentDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: StudentViewModel
    private lateinit var studentRecyclerViewAdapter: StudentRecyclerViewAdapter

    private var isListItemClicked:Boolean = false
    private lateinit var selectedStudent:Student

    //Viewbinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = StudentDatabase.getInstance(application).studentDao()
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this,factory).get(StudentViewModel::class.java)

        binding.btnSave.setOnClickListener {

            if(isListItemClicked){
                updateStudent()
            }else{
                saveStudent()
            }
            clearInput()
        }

        binding.btnClear.setOnClickListener {

            if(isListItemClicked){
                deleteStudent()
                clearInput()
            }else{
                clearInput()
            }

        }

        setAdapter()

    }

    fun saveStudent(){
        binding.apply {
            viewModel.insertStudent(
                Student(
                    0,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
        }
    }

    fun updateStudent(){

        binding.apply {
            viewModel.updateStudent(
                Student(
                    selectedStudent.id,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )

            isListItemClicked = false
            btnSave.text = "Save"
            btnClear.text = "Clear"
        }

    }

    fun deleteStudent(){

        binding.apply {
            viewModel.deleteStudent(
                Student(
                    selectedStudent.id,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )


            isListItemClicked = false
            btnSave.text = "Save"
            btnClear.text = "Clear"
        }

    }

    fun clearInput(){
        binding.apply {
            etName.setText("")
            etEmail.setText("")
        }

    }

    fun setAdapter(){

            binding.studentRv.layoutManager=LinearLayoutManager(this)
            studentRecyclerViewAdapter=StudentRecyclerViewAdapter{
                    studentItem: Student -> studentClicked(studentItem) }
            binding.studentRv.adapter=studentRecyclerViewAdapter

        listOfStudents()
    }

    fun listOfStudents(){

            viewModel.getAllStudents.observe(this, Observer {
                studentRecyclerViewAdapter.setList(it)
                studentRecyclerViewAdapter.notifyDataSetChanged()
            })

    }

    fun studentClicked(student:Student){
        Toast.makeText(this, "Student name is $student", Toast.LENGTH_SHORT).show()

        selectedStudent = student
        isListItemClicked=true

        binding.apply {
            etName.setText(selectedStudent.name)
            etEmail.setText(selectedStudent.email)
            btnSave.text = "Update"
            btnClear.text = "Delete"
        }
    }
}