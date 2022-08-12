package com.school.studentsregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.school.studentsregister.db.StudentDao
import java.lang.IllegalArgumentException

class StudentViewModelFactory(private val studentDao: StudentDao):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(StudentViewModel::class.java)){

            return StudentViewModel(studentDao) as T

        }else{
           throw IllegalArgumentException("Unknown Viewmodel class")
        }


    }
}