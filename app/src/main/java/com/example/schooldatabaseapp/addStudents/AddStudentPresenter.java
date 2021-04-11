package com.example.schooldatabaseapp.addStudents;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.model.EntityClassRoom;
import com.example.schooldatabaseapp.repositories.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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

        repository.insert(student);

    }

    @Override
    public List<EntityClassRoom> getClassRooms() {
        List<EntityClassRoom> resultClassRoomList = new ArrayList<>();
        repository.getAllClassRoom()
                .flatMap(new Function<List<EntityClassRoom>, SingleSource<List<EntityClassRoom>>>() {
                    @Override
                    public SingleSource<List<EntityClassRoom>> apply(@NonNull List<EntityClassRoom> classRoomList) throws Exception {
                        return Single.just(classRoomList);
                    }
                }).subscribe(new Consumer<List<EntityClassRoom>>() {
            @Override
            public void accept(List<EntityClassRoom> classRoomList) throws Exception {
                resultClassRoomList.addAll(classRoomList);
            }
        });


        return resultClassRoomList;
    }


}
