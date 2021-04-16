package com.example.schooldatabaseapp.classRoom.mvvvm;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.room.repository.RoomClassRoomRepository;

import java.util.List;

@SuppressLint("CheckResult")

public class ClassRoomListViewModel extends ViewModel {

    public MutableLiveData<List<ClassRoom>> classrooms;
    private ClassRoomRepository repository;

    public ClassRoomListViewModel() {
        super();
        repository = RoomClassRoomRepository.getInstance();
    }

    public LiveData<List<ClassRoom>> getUsers() {
        if (classrooms == null) {
            classrooms = new MutableLiveData<List<ClassRoom>>();
            loadClassRooms();
        }
        return classrooms;
    }

    private void loadClassRooms() {

        repository.getAllClassrooms();

    }


}
