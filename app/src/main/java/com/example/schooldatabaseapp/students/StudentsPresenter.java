package com.example.schooldatabaseapp.students;

import android.content.Context;
import android.util.Log;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.List;

public class StudentsPresenter implements StudentsContract.Presenter {

    private static final String TAG = "My_tag";
    private StudentsRepository repository;
    private StudentsContract.View view;

    public StudentsPresenter(StudentsContract.View callBack, Context context) {
        this.repository = new DatabaseStudentsRepository(context);
        this.view = callBack;

    }


    @Override
    public void updateStudent() {

        view.updateStudents(repository.getAll());
        Log.d(TAG, "updateStudent: ");
    }

    @Override
    public List<ClassRoom> getClassRooms() {
        return repository.getAllClassRoom();
    }


    @Override
    public void onItemClickListener(Student student) {

        view.openStudentsDetailsFragment(student);

    }

    @Override
    public void showOtherFragment() {
        view.openOtherFragment();
    }

    @Override
    public void onClassItemClickListener(ClassRoom classRoom) {


    }
}
