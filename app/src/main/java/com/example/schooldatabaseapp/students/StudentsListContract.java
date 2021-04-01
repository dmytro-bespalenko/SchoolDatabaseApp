package com.example.schooldatabaseapp.students;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface StudentsListContract {

    interface View {


        void updateStudents(List<Student> all);


        void openStudentsDetailsFragment(Student student);

        void openAddStudentFragment();


    }

    interface Presenter {

        void updateStudent();

        void openAddStudentFragment();

        void deleteStudent(List<Student> studentsList, int adapterPosition);

        void openDetailsStudentFragment(Student student);
    }
}
