package com.example.schooldatabaseapp.presenter;

import com.example.schooldatabaseapp.ClassRoomContract;
import com.example.schooldatabaseapp.DatabaseRepository;

public class ClassRoomPresenter implements ClassRoomContract.Presenter {

    private ClassRoomContract.View mView;
    private ClassRoomContract.Repository mRepository;


    public ClassRoomPresenter(ClassRoomContract.View mView, DatabaseRepository repository) {
        this.mView = mView;
        this.mRepository = repository;

    }

    @Override
    public void onButtonWasClicked() {

    }


}
