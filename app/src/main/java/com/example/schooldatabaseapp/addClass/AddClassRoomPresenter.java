package com.example.schooldatabaseapp.addClass;

import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.model.DatabaseClassRoomRepository;

public class AddClassRoomPresenter {

    private final ClassRoomRepository repository;

    public AddClassRoomPresenter(ClassRoomRepository repository) {
        this.repository = repository;
    }

    public void addNewClassRoom(String className, int classNumber, int floor) {

//        repository.insert();

    }
}
