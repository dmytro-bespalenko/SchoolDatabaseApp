package com.example.schooldatabaseapp.detailsStudent;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")

public class DetailsStudentPresenter implements DetailsStudentContract.Presenter {

    private static final String TAG = "My_tag";
    private DetailsStudentContract.View view;
    private StudentsRepository repository;

    public DetailsStudentPresenter(DetailsStudentContract.View callBack, StudentsRepository roomStudentsRepository) {
        this.view = callBack;
        repository = roomStudentsRepository;
    }

    @Override
    public void openEditStudentFragment() {

        view.openEditStudentFragment();

    }

    @Override
    public void deleteStudent(Student student) {
        repository.delete(student.getId())
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    @Override
    public List<ClassRoom> getAllClassRooms() {


        return repository.getAllClassRoom().subscribeOn(Schedulers.io())
                .map(new Function<List<ClassRoom>, List<ClassRoom>>() {
                    @Override
                    public List<ClassRoom> apply(@NonNull List<ClassRoom> classRoomList) throws Exception {
                        return classRoomList;
                    }
                }).toObservable().blockingSingle();
    }


}
