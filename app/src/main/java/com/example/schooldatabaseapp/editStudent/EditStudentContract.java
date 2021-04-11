package com.example.schooldatabaseapp.editStudent;

import com.example.schooldatabaseapp.model.EntityClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface EditStudentContract {

    interface Presenter {

        List<EntityClassRoom> getClassRooms();
        void saveEditStudent(Student student);
    }

    interface View {

    }

}
