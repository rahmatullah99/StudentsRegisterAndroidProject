package com.school.studentsregister.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    suspend fun insertStudent(student:Student)

    @Update
    suspend fun updateStudent(student:Student)

    @Delete
    suspend fun deleteStudent(student:Student)

    @Query("SELECT * FROM student_data_table")
    fun getAllStudents():LiveData<List<Student>>

}