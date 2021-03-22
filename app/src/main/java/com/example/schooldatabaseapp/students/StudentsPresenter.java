package com.example.schooldatabaseapp.students;

import android.content.Context;
import android.util.Log;

import com.example.schooldatabaseapp.dbase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.StudentsRepository;

public class StudentsPresenter implements StudentsContract.Presenter {

    private static final String TAG = "My_tag";
    private StudentsRepository repository;
    private StudentsContract.View view;

    public StudentsPresenter(StudentsContract.View view, Context context) {
        this.repository = new DatabaseStudentsRepository(context);
        this.view = view;
    }


    @Override
    public void updateStudent() {

        view.updateStudents(repository.getAll());
        Log.d(TAG, "updateStudent: ");
    }
}
