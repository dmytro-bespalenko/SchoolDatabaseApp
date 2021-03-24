package com.example.schooldatabaseapp.detailsStudent;

import android.content.Context;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;

public class DetailsStudentPresenter implements DetailsStudentContract.Presenter {


    private DetailsStudentContract.View view;
    private DatabaseStudentsRepository repository;

    public DetailsStudentPresenter(DetailsStudentContract.View callBack, Context context) {
        this.view = callBack;
        repository = new DatabaseStudentsRepository(context);
    }

    @Override
    public void showOtherFragment() {

        view.openOtherFragment();

    }
}
