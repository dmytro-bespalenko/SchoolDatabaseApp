package com.example.schooldatabaseapp.addClass;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.room.repository.RoomClassRoomRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        repository.insert(new ClassRoom(className, classNumber, 5, floor))
                .subscribeOn(Schedulers.io())
                .subscribe()

        ;

    }
}
