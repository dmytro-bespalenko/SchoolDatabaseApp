package com.example.schooldatabaseapp.addStudents;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
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
    private DatabaseStudentsRepository repository;
    private AddStudentContract.View view;

    public AddStudentPresenter(AddStudentContract.View callBack) {
        this.view = callBack;
        repository = DatabaseStudentsRepository.getInstance();
    }

    @Override
    public void addNewStudent(Student student) {

        repository.insert(student)
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    @Override
    public List<ClassRoom> getClassRooms() {
        List<ClassRoom> resultClassRoomList = new ArrayList<>();
        repository.getAllClassRoom()
                .flatMap(new Function<List<ClassRoom>, SingleSource<List<ClassRoom>>>() {
                    @Override
                    public SingleSource<List<ClassRoom>> apply(@NonNull List<ClassRoom> classRoomList) throws Exception {
                        return Single.just(classRoomList);
                    }
                }).subscribe(new Consumer<List<ClassRoom>>() {
            @Override
            public void accept(List<ClassRoom> classRoomList) throws Exception {
                resultClassRoomList.addAll(classRoomList);
            }
        });


        return resultClassRoomList;
    }


}
