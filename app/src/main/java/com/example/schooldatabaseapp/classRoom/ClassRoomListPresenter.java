package com.example.schooldatabaseapp.classRoom;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.dataBase.DatabaseClassRoomRepository;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


@SuppressLint("CheckResult")

public class ClassRoomListPresenter implements ClassRoomListContract.Presenter {

    private static final String TAG = "My_tag";
    private final ClassRoomListContract.View view;
    private final ClassRoomRepository repository;
    private Executor executor;
    private Handler handler;

    public ClassRoomListPresenter(ClassRoomListContract.View callBack) {
        this.repository = DatabaseClassRoomRepository.getInstance();
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
                        Log.d(TAG, "run: updateClassRooms " + Thread.currentThread().getName());
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
                Log.d(TAG, "run: " + Thread.currentThread().getName() + " " + getClass().getName());

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
                Log.d(TAG, "run: " + Thread.currentThread().getName() + " " + getClass().getName());

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
    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();

        repository.getAllStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Student>>() {
                    @Override
                    public void accept(List<Student> classRoomList) throws Exception {
                        Log.d(TAG, "Presenter " + Thread.currentThread().getName());

                        result.addAll(classRoomList);
                    }
                });


        return result;
    }

    @Override
    public void deleteClassRoom(ClassRoom classRoom) {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                repository.delete(classRoom.getClassId());
                Log.d(TAG, "run: " + Thread.currentThread().getName() + " " + getClass().getName());

            }
        });
    }

    @Override
    public void deleteStudent(Student student) {
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.deleteStudent(student.getId());
                Log.d(TAG, "run: " + Thread.currentThread().getName() + " " + getClass().getName());

            }
        });
    }


}
