package com.example.schooldatabaseapp.editClassRoom;

import android.content.Context;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.dbase.DatabaseClassRoomRepository;

import java.util.List;

public class EditClassRoomPresenter implements EditClassRoomContract.Presenter {

    private final ClassRoomRepository repository;
    private EditClassRoomContract.View view;

    public EditClassRoomPresenter(EditClassRoomContract.View view, Context context) {
        this.repository = new DatabaseClassRoomRepository(context);
        this.view = view;
    }


    @Override
    public void editClassRoom(ClassRoom classRoom) {
        repository.update(classRoom);
    }

    @Override
    public void onEditButtonWasClicked(List<ClassRoom> classRooms, int adapterPosition) {


    }



}
