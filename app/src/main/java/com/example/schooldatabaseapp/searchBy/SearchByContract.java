package com.example.schooldatabaseapp.searchBy;

import com.example.schooldatabaseapp.model.EntityClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface SearchByContract {

    interface Presenter {

        void updateClassRooms();

        void updateStudents();

        void openDetailsStudentFragment(Student student);

        void openStudentsListFragment(EntityClassRoom classRoom);
    }

    interface View {

        void updateStudents(List<Student> all);

        void openClassRoomDetailsFragment(EntityClassRoom classRoom);

        void updateClassRooms(List<EntityClassRoom> allClassRoom);

        void openDetailsStudentFragment(Student student);
    }

}
