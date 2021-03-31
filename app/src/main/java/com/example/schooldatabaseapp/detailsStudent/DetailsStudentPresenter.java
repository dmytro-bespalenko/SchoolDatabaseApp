package com.example.schooldatabaseapp.detailsStudent;

import android.content.Context;
import android.util.Log;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DetailsStudentPresenter implements DetailsStudentContract.Presenter {

    private static final String TAG = "My_tag";
    private DetailsStudentContract.View view;
    private DatabaseStudentsRepository repository;
    private Executor executor;

    public DetailsStudentPresenter(DetailsStudentContract.View callBack, Context context) {
        this.view = callBack;
        repository = new DatabaseStudentsRepository(context);
    }

    @Override
    public void openEditStudentFragment() {

        view.openEditStudentFragment();

    }

    @Override
    public void deleteStudent(Student student) {
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (repository.getAll().size() > 0) {
                    view.deleteCurrentStudent(repository.getAll(), repository.delete(student.getId()));
                    Log.d(TAG, "run: " + Thread.currentThread().getName() + " delete student " + getClass().getName());

                }
            }
        });

    }

    @Override
    public List<ClassRoom> getAllClassRooms() {
        return repository.getAllClassRoom();
    }


}
