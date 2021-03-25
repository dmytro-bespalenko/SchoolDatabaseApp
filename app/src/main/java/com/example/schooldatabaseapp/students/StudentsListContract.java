package com.example.schooldatabaseapp.students;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface StudentsListContract {

    interface View {


        void updateStudents(List<Student> all);

        void deleteStudent(int position);

        void openStudentsDetailsFragment(Student student);

    }

    interface Presenter {

        void updateStudent();


        List<ClassRoom> getClassRooms();

        void onItemClickListener(Student student);


        void onItemWasLongClick(List<Student> studentsList, int adapterPosition);

    }
}
