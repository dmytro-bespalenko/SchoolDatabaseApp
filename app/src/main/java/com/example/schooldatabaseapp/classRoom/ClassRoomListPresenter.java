package com.example.schooldatabaseapp.classRoom;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.dataBase.DatabaseClassRoomRepository;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


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
        handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                view.updateRooms(repository.getAll());
                Log.d(TAG, "run: " + Thread.currentThread().getName() + " Handler " + getClass().getName());

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
        return repository.getAllStudents();
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
