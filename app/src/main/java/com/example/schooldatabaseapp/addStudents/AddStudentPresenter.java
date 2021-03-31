package com.example.schooldatabaseapp.addStudents;

import android.content.Context;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddStudentPresenter implements AddStudentContract.Presenter {


    private DatabaseStudentsRepository repository;
    private AddStudentContract.View view;
    private Executor executor;

    public AddStudentPresenter(AddStudentContract.View callBack, Context context) {

        this.view = callBack;
        repository = new DatabaseStudentsRepository(context);

    }

    @Override
    public void addNewStudent(String firstName, String lastName, int selectedClassId, String gender, int age) {
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.insert(new Student(firstName, lastName, selectedClassId, gender, age));

            }
        });

    }

    @Override
    public List<ClassRoom> getClassRooms() {


        return repository.getAllClassRoom();
    }



}
