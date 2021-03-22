package com.example.schooldatabaseapp.students;

import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface StudentsContract {

    interface View {

        void updateStudents(List<Student> all);

        void deleteStudent(int position);

        void openFragment(Student student);

        void openClassRoomDetailsFragment(Student student);

    }

    interface Presenter {

        void updateStudent();

    }
}
