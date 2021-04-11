package com.example.schooldatabaseapp.addStudents;

import com.example.schooldatabaseapp.model.EntityClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

interface AddStudentContract {

    interface Presenter {

        void addNewStudent(Student student);

        List<EntityClassRoom> getClassRooms();

    }

    interface View {


    }

}
