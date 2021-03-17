package com.example.schooldatabaseapp.addClass;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.model.DatabaseClassRoomRepository;

public class AddClassRoomPresenter  implements AddClassRoomContract.Presenter{

    private final ClassRoomRepository repository;

    public AddClassRoomPresenter(ClassRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewClassRoom(String className, int classNumber, int floor) {

        repository.insert(new ClassRoom(className, classNumber, 5, floor));

    }
}
