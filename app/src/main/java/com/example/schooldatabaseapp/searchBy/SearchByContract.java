package com.example.schooldatabaseapp.searchBy;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.room.entity.EntityClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface SearchByContract {

    interface Presenter {

        void updateClassRooms();

        void updateStudents();

        void openDetailsStudentFragment(Student student);

        void openStudentsListFragment(ClassRoom classRoom);
    }

    interface View {

        void updateStudents(List<Student> all);

        void openClassRoomDetailsFragment(ClassRoom classRoom);

        void updateClassRooms(List<ClassRoom> allClassRoom);

        void openDetailsStudentFragment(Student student);
    }

}
