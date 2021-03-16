package com.example.schooldatabaseapp.presenter;

import com.example.schooldatabaseapp.model.ClassRoomContract;
import com.example.schooldatabaseapp.model.DatabaseRepository;

public class ClassRoomPresenter implements ClassRoomContract.Presenter {

    private ClassRoomContract.View mView;
    private ClassRoomContract.Repository mRepository;

    public ClassRoomPresenter(ClassRoomContract.View mView, DatabaseRepository repository) {
        this.mView = mView;
        this.mRepository = repository;

    }


    @Override
    public void onButtonWasClicked(int id) {

        mRepository.delete(id);

    }

    @Override
    public void onAddButtonClicked() {


    }


}
