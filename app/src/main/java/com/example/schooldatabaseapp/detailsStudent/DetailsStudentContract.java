package com.example.schooldatabaseapp.detailsStudent;

import com.example.schooldatabaseapp.model.EntityClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

import io.reactivex.Single;

public interface DetailsStudentContract {

    interface View {

        void openEditStudentFragment();

        void deleteCurrentStudent(List<Student> students, Single<Integer> delete);
    }

    interface Presenter {

        void openEditStudentFragment();

        void deleteStudent(Student student);


        List<EntityClassRoom> getAllClassRooms();

    }

}
