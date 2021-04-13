package com.example.schooldatabaseapp.classRoom;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


@SuppressLint("CheckResult")

public class ClassRoomListPresenter implements ClassRoomListContract.Presenter {

    private static final String TAG = "My_tag";
    private final ClassRoomListContract.View view;
    private final ClassRoomRepository repository;
    private Executor executor;

    public ClassRoomListPresenter(ClassRoomListContract.View callBack, ClassRoomRepository roomRepository) {
        this.repository = roomRepository;
        this.view = callBack;
    }


    @Override
    public void updateClassRooms() {
        repository.getAllClassrooms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ClassRoom>>() {
                    @Override
                    public void accept(List<ClassRoom> classRoomList) throws Exception {
                        view.updateRooms(classRoomList);
                    }
                });
    }


    @Override
    public void openEditFragment(ClassRoom classRoom) {
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                view.openClassRoomEditFragment(classRoom);

            }
        });
    }

    @Override
    public void onItemClickListener(ClassRoom classRoom) {
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                view.openClassRoomDetailsFragment(classRoom);

            }
        });

    }

    @Override
    public void showAddClassFragment() {
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                view.openAddClassRoomFragment();

            }
        });
    }

    @Override
    public void showAddStudentFragment() {
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                view.openAddStudentFragment();
                Log.d(TAG, "run: " + Thread.currentThread().getName() + " " + getClass().getName());

            }
        });
    }


    @Override
    public void deleteClassRoom(ClassRoom classRoom) {

        repository.delete(classRoom.getClassId())
                .subscribeOn(Schedulers.io())
                .subscribe();

    }



}
