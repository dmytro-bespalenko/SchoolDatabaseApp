package com.example.schooldatabaseapp.editStudent;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface EditStudentContract {

    interface Presenter {

        List<ClassRoom> getClassRooms();
        void saveEditStudent(Student student);
    }

    interface View {

    }

}
