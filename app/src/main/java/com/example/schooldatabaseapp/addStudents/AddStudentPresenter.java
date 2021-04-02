package com.example.schooldatabaseapp.addStudents;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")

public class AddStudentPresenter implements AddStudentContract.Presenter {


    private DatabaseStudentsRepository repository;
    private AddStudentContract.View view;
    private Executor executor;

    public AddStudentPresenter(AddStudentContract.View callBack) {
        this.view = callBack;
        repository = DatabaseStudentsRepository.getInstance();
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
        List<ClassRoom> classRoomList = new ArrayList<>();
        repository.getAllClassRoom()
                .subscribeOn(Schedulers.io())
                .map(new Function<List<ClassRoom>, List<ClassRoom>>() {
                    @Override
                    public List<ClassRoom> apply(@NonNull List<ClassRoom> classRoomList) throws Exception {
                        return classRoomList;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe((Consumer<List<ClassRoom>>) classRoomList::addAll);
        return classRoomList;
    }


}
