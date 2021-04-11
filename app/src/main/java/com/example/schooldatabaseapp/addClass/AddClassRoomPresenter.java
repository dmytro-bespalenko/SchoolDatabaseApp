package com.example.schooldatabaseapp.addClass;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.model.EntityClassRoom;
import com.example.schooldatabaseapp.model.RoomClassRoomRepository;
import com.example.schooldatabaseapp.repositories.DatabaseClassRoomRepository;
import com.example.schooldatabaseapp.model.ClassRoomDao;

public class AddClassRoomPresenter implements AddClassRoomContract.Presenter {

    private static final String TAG = "MyTag";
    private final ClassRoomRepository repository;
    private AddClassRoomContract.View view;

    public AddClassRoomPresenter(AddClassRoomContract.View callBack, RoomClassRoomRepository roomRepository) {
        this.repository = roomRepository;
        this.view = callBack;
    }

    @Override
    public void addNewClassRoom(String className, int classNumber, int floor) {
        repository.insert(new ClassRoom(className, classNumber, 5, floor));

    }
}
