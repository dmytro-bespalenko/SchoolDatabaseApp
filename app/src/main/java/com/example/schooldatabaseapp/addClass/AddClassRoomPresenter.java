package com.example.schooldatabaseapp.addClass;

import android.util.Log;

import com.example.schooldatabaseapp.dataBase.DatabaseClassRoomRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddClassRoomPresenter  implements AddClassRoomContract.Presenter{

    private static final String TAG = "MyTag";
    private final ClassRoomRepository repository;
    private AddClassRoomContract.View view;
    private Executor executor;

    public AddClassRoomPresenter(AddClassRoomContract.View callBack) {
        this.repository = DatabaseClassRoomRepository.getInstance();
        this.view = callBack;
    }

    @Override
    public void addNewClassRoom(String className, int classNumber, int floor) {
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.insert(new ClassRoom(className, classNumber, 5, floor));
                Log.d(TAG, "run: " + Thread.currentThread().getName());
            }
        });


    }
}
