package com.example.schooldatabaseapp.addStudents;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.room.entity.EntityClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

interface AddStudentContract {

    interface Presenter {

        void addNewStudent(Student student);

        List<ClassRoom> getClassRooms();

    }

    interface View {


    }

}
