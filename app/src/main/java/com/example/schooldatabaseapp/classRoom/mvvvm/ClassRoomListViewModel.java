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

    private ClassRoomRepository repository;
    public MutableLiveData<List<ClassRoom>> classrooms = new MutableLiveData<List<ClassRoom>>();

    public ClassRoomListViewModel() {
        super();
        repository = RoomClassRoomRepository.getInstance();
    }

    public void getClassRooms() {
        if (classrooms == null) {
            classrooms.postValue(loadClassRooms());
        }
    }

    private void loadClassRooms() {

        repository.getAllClassrooms();

    }


}
