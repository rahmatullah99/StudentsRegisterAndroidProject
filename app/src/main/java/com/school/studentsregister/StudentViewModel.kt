package com.school.studentsregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.studentsregister.db.Student
import com.school.studentsregister.db.StudentDao
import kotlinx.coroutines.launch

class StudentViewModel(private val studentDao: StudentDao) : ViewModel() {

    val getAllStudents = studentDao.getAllStudents()

    fun insertStudent(student: Student){
        viewModelScope.launch {
            studentDao.insertStudent(student)
        }
    }

    fun updateStudent(student: Student){
        viewModelScope.launch {
            studentDao.updateStudent(student)
        }
    }

    fun deleteStudent(student: Student){
        viewModelScope.launch {
            studentDao.deleteStudent(student)
        }
    }

}