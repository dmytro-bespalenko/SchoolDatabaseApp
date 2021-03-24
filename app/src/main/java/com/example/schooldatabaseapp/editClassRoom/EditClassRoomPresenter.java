package com.example.schooldatabaseapp.editClassRoom;

import android.content.Context;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.dataBase.DatabaseClassRoomRepository;

public class EditClassRoomPresenter implements EditClassRoomContract.Presenter {

    private final ClassRoomRepository repository;
    private EditClassRoomContract.View view;

    public EditClassRoomPresenter(EditClassRoomContract.View callback, Context context) {
        this.repository = new DatabaseClassRoomRepository(context);
        this.view = callback;
    }


    @Override
    public void editClassRoom(ClassRoom classRoom) {
        repository.update(classRoom);
    }


}
