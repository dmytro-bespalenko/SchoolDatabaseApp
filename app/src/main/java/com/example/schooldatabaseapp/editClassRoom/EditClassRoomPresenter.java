package com.example.schooldatabaseapp.editClassRoom;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.room.repository.RoomClassRoomRepository;

@SuppressLint("CheckResult")


public class EditClassRoomPresenter implements EditClassRoomContract.Presenter {

    private static final String TAG = "My_tag";
    private final ClassRoomRepository repository;
    private EditClassRoomContract.View view;

    public EditClassRoomPresenter(EditClassRoomContract.View callback, ClassRoomRepository repository) {
        this.repository = repository;
        this.view = callback;
    }


    @Override
    public void editClassRoom(ClassRoom classRoom) {
        repository.update(classRoom);

    }


}
