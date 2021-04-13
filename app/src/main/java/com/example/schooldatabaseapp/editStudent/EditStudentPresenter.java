package com.example.schooldatabaseapp.editStudent;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.StudentsRepository;
import com.example.schooldatabaseapp.room.entity.EntityClassRoom;
import com.example.schooldatabaseapp.repositories.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.room.repository.RoomStudentsRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")

public class EditStudentPresenter implements EditStudentContract.Presenter {

    private static final String TAG = "My_tag";
    private EditStudentContract.View view;
    private StudentsRepository studentsRepository;

    public EditStudentPresenter(EditStudentContract.View callBack, StudentsRepository repository) {
        this.view = callBack;
        this.studentsRepository = repository;
    }

    @Override
    public List<ClassRoom> getClassRooms() {
        List<ClassRoom> resultClassRoomList = new ArrayList<>();
        studentsRepository.getAllClassRoom()
                .subscribeOn(Schedulers.io())
                .map(new Function<List<ClassRoom>, List<ClassRoom>>() {
                    @Override
                    public List<ClassRoom> apply(@NonNull List<ClassRoom> classRoomList) throws Exception {
                        resultClassRoomList.addAll(classRoomList);
                        return classRoomList;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        return resultClassRoomList;
    }

    @Override
    public void saveEditStudent(Student student) {

        studentsRepository.update(student)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
