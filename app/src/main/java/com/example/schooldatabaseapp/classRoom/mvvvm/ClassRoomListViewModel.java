package com.example.schooldatabaseapp.classRoom.mvvvm;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.room.repository.RoomClassRoomRepository;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")

public class ClassRoomListViewModel extends ViewModel {

    private final ClassRoomRepository repository;
    private final MutableLiveData<List<ClassRoom>> classrooms = new MutableLiveData<List<ClassRoom>>();

    public ClassRoomListViewModel(RoomClassRoomRepository repository) {
        super();
       this.repository = repository;
    }


    public LiveData<List<ClassRoom>> getClassrooms() {
        return classrooms;
    }

    public void updateClassrooms() {

        repository.getAllClassrooms()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<ClassRoom>>() {
                    @Override
                    public void accept(List<ClassRoom> classRoomList) throws Exception {
                        classrooms.postValue(classRoomList);
                    }
                });
    }


    public void deleteClassRoom(ClassRoom classRoom) {
        repository.delete(classRoom.getClassId())
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    public void onItemClickListener(ClassRoom classRoom) {

    }
}
