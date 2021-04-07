package com.example.schooldatabaseapp.editClassRoom;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.repositories.DatabaseClassRoomRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")


public class EditClassRoomPresenter implements EditClassRoomContract.Presenter {

    private static final String TAG = "My_tag";
    private final ClassRoomRepository repository;
    private EditClassRoomContract.View view;

    public EditClassRoomPresenter(EditClassRoomContract.View callback) {
        this.repository = DatabaseClassRoomRepository.getInstance();
        this.view = callback;
    }


    @Override
    public void editClassRoom(ClassRoom classRoom) {
        repository.update(classRoom)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }


}
