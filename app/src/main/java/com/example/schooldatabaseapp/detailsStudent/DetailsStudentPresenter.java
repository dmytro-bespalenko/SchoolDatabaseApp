package com.example.schooldatabaseapp.detailsStudent;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.model.EntityClassRoom;
import com.example.schooldatabaseapp.repositories.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")

public class DetailsStudentPresenter implements DetailsStudentContract.Presenter {

    private static final String TAG = "My_tag";
    private DetailsStudentContract.View view;
    private DatabaseStudentsRepository repository;

    public DetailsStudentPresenter(DetailsStudentContract.View callBack) {
        this.view = callBack;
        repository = DatabaseStudentsRepository.getInstance();
    }

    @Override
    public void openEditStudentFragment() {

        view.openEditStudentFragment();

    }

    @Override
    public void deleteStudent(Student student) {
        repository.delete(student);


    }

    @Override
    public List<EntityClassRoom> getAllClassRooms() {

        List<EntityClassRoom> result = new ArrayList<>();
        repository.getAllClassRoom()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<EntityClassRoom>>() {
            @Override
            public void accept(List<EntityClassRoom> classRoomList) throws Exception {
                result.addAll(classRoomList);
            }
        });

        return result;
    }


}
