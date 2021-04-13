package com.example.schooldatabaseapp.addStudents;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.StudentsRepository;
import com.example.schooldatabaseapp.room.entity.EntityClassRoom;
import com.example.schooldatabaseapp.repositories.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.room.repository.RoomStudentsRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")

public class AddStudentPresenter implements AddStudentContract.Presenter {


    private static final String TAG = "My_tag";
    private StudentsRepository repository;
    private AddStudentContract.View view;

    public AddStudentPresenter(AddStudentContract.View callBack, StudentsRepository repository) {
        this.view = callBack;
        this.repository = repository;
    }

    @Override
    public void addNewStudent(Student student) {

        repository.insert(student)
                .subscribeOn(Schedulers.io())
                .subscribe()
        ;

    }

    @Override
    public List<ClassRoom> getClassRooms() {
        List<ClassRoom> resultClassRoomList = new ArrayList<>();
        repository.getAllClassRoom()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<ClassRoom>>() {
                    @Override
                    public void accept(List<ClassRoom> classRoomList) throws Exception {
                        resultClassRoomList.addAll(classRoomList);
                    }
                });


        return resultClassRoomList;
    }


}
