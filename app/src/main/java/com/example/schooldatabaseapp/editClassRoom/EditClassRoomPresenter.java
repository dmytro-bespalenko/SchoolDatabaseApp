package com.example.schooldatabaseapp.editClassRoom;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.dataBase.DatabaseClassRoomRepository;


public class EditClassRoomPresenter implements EditClassRoomContract.Presenter {

    private static final String TAG = "My_tag";
    private final ClassRoomRepository repository;
    private EditClassRoomContract.View view;
    private Handler handler;

    public EditClassRoomPresenter(EditClassRoomContract.View callback, Context context) {
        this.repository = new DatabaseClassRoomRepository(context);
        this.view = callback;
    }


    @Override
    public void editClassRoom(ClassRoom classRoom) {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                repository.update(classRoom);
                Log.d(TAG, "run: " + Thread.currentThread().getName() + " HandlerUpdate " + getClass().getName());

            }
        });
    }


}
