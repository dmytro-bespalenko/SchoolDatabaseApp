package com.example.schooldatabaseapp.detailsStudent;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")

public class DetailsStudentPresenter implements DetailsStudentContract.Presenter {

    private static final String TAG = "My_tag";
    private DetailsStudentContract.View view;
    private DatabaseStudentsRepository repository;
    private Executor executor;

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
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
//                    view.deleteCurrentStudent(repository.getAll(), repository.delete(student.getId()));
                } catch (IndexOutOfBoundsException ignored) {

                }
            }
        });

    }

    @Override
    public List<ClassRoom> getAllClassRooms() {
        List<ClassRoom> resultClassroomList = new ArrayList<>();
        repository.getAllClassRoom()
                .subscribeOn(Schedulers.io())
                .map(new Function<List<ClassRoom>, List<ClassRoom>>() {
                    @Override
                    public List<ClassRoom> apply(@NonNull List<ClassRoom> classRoomList) throws Exception {
                        resultClassroomList.addAll(classRoomList);
                        return resultClassroomList;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        return resultClassroomList;
    }


}
