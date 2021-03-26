package com.example.schooldatabaseapp.searchBy;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface SearchByContract {

    interface Presenter {

        void updateClassRooms();

        void updateStudents();

        void onItemStudentClickListener(Student student);

        void onItemClassRoomClickListener(ClassRoom classRoom);
    }

    interface View {
        void updateStudents(List<Student> all);

        void openClassRoomDetailsFragment(ClassRoom classRoom);

        void openStudentsDetailsFragment(Student student);

        void updateClassRooms(List<ClassRoom> allClassRoom);
    }

}
