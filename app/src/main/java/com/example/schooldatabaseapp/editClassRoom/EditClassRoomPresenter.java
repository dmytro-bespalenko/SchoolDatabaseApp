package com.example.schooldatabaseapp.editClassRoom;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.model.ClassRoomDao;
import com.example.schooldatabaseapp.model.EntityClassRoom;
import com.example.schooldatabaseapp.repositories.DatabaseClassRoomRepository;

@SuppressLint("CheckResult")


public class EditClassRoomPresenter implements EditClassRoomContract.Presenter {

    private static final String TAG = "My_tag";
    private final ClassRoomDao repository;
    private EditClassRoomContract.View view;

    public EditClassRoomPresenter(EditClassRoomContract.View callback) {
        this.repository = DatabaseClassRoomRepository.getInstance();
        this.view = callback;
    }


    @Override
    public void editClassRoom(EntityClassRoom classRoom) {
        repository.update(classRoom);

    }


}
