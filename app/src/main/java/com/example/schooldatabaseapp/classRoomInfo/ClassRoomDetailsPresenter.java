package com.example.schooldatabaseapp.classRoomInfo;

import android.content.Context;

import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.model.DatabaseClassRoomRepository;

public class ClassRoomDetailsPresenter implements ClassRoomDetailsContract.Presenter {

    private ClassRoomRepository repository;
    private ClassRoomDetailsContract.View view;

    public ClassRoomDetailsPresenter(ClassRoomDetailsContract.View view, Context context) {
        this.view = view;
        this.repository = new DatabaseClassRoomRepository(context);
    }


}
