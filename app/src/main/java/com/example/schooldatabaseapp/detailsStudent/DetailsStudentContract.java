package com.example.schooldatabaseapp.detailsStudent;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public interface DetailsStudentContract {

    interface View {

        void openEditStudentFragment();

        void deleteCurrentStudent(List<Student> students, Single<Integer> delete);
    }

    interface Presenter {

        void openEditStudentFragment();

        void deleteStudent(Student student);


        List<ClassRoom> getAllClassRooms();

    }

}
