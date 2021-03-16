package com.example.schooldatabaseapp.classRoom;

import com.example.schooldatabaseapp.model.ClassRoomRepository;

public class ClassRoomPresenter implements ClassRoomContract.Presenter {

    private ClassRoomContract.View mView;
    private ClassRoomRepository mClassRoomRepository;

    public ClassRoomPresenter(ClassRoomContract.View mView, ClassRoomRepository repository) {
        this.mView = mView;
        this.mClassRoomRepository = repository;
    }


    @Override
    public void onButtonWasClicked(int id) {


    }

    @Override
    public void onAddButtonClicked() {


    }

    @Override
    public void refresh() {
        mView.updateRooms(mClassRoomRepository.getAll());
    }


}
