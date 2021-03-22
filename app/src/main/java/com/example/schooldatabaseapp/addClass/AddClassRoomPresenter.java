package com.example.schooldatabaseapp.addClass;

import android.content.Context;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.dbase.DatabaseClassRoomRepository;

public class AddClassRoomPresenter  implements AddClassRoomContract.Presenter{

    private final ClassRoomRepository repository;
    private AddClassRoomContract.View view;

    public AddClassRoomPresenter(AddClassRoomContract.View view, Context context) {
        this.repository = new DatabaseClassRoomRepository(context);
        this.view = view;
    }

    @Override
    public void addNewClassRoom(String className, int classNumber, int floor) {

        repository.insert(new ClassRoom(className, classNumber, 5, floor));

    }
}
