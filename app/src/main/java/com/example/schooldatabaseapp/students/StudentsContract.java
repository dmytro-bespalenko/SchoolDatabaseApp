package com.example.schooldatabaseapp.students;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface StudentsContract {

    interface View {


        void updateStudents(List<Student> all);

        void deleteStudent(int position);

        void openStudentEditFragment(Student student);


        void openStudentsDetailsFragment(Student student);

        void openOtherFragment();
    }

    interface Presenter {

        void updateStudent();


        List<ClassRoom> getClassRooms();

        void onItemClickListener(Student student);

        void showOtherFragment();

        void onClassItemClickListener(ClassRoom classRoom);
    }
}
