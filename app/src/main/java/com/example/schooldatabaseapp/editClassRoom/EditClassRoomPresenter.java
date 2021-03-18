package com.example.schooldatabaseapp.editClassRoom;

import android.content.Context;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.model.DatabaseClassRoomRepository;

import java.util.List;

public class EditClassRoomPresenter implements EditClassRoomContract.Presenter {

    private final ClassRoomRepository repository;
    private EditClassRoomContract.View view;

    public EditClassRoomPresenter(EditClassRoomContract.View view, Context context) {
        this.repository = new DatabaseClassRoomRepository(context);
        this.view = view;
    }



    @Override
    public void editClassRoom(int pos) {
        repository.update(repository.getById(pos));
    }

    @Override
    public void onEditButtonWasClicked(List<ClassRoom> classRooms, int adapterPosition) {


    }

    @Override
    public void updateClassRooms() {
        view.updateRooms(repository.getAll());

    }


}
