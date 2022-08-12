package com.school.studentsregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.school.studentsregister.databinding.ListItemBinding
import com.school.studentsregister.db.Student



class StudentRecyclerViewAdapter(private val studentClicked: (Student) -> Unit) : RecyclerView.Adapter<StudentViewHolder>() {

    var studentList = ArrayList<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position], studentClicked)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun setList(students: List<Student>){
        studentList.clear()
        studentList.addAll(students)
    }
}

class StudentViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(student: Student,studentClicked:(Student)->Unit){

        binding.apply {
            studentName.setText(student.name)
            StudentEmail.setText(student.email)

            root.setOnClickListener {
                studentClicked(student)
            }
        }


    }
}