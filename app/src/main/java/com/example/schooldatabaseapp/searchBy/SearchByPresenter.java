package com.example.schooldatabaseapp.searchBy;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.model.EntityClassRoom;
import com.example.schooldatabaseapp.repositories.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")


public class SearchByPresenter implements SearchByContract.Presenter {

    private static final String TAG = "My_Tag";
    private SearchByContract.View view;
    private StudentsDao repository;

    public SearchByPresenter(SearchByContract.View callBack) {
        this.view = callBack;
        repository = DatabaseStudentsRepository.getInstance();
    }

    @Override
    public void updateClassRooms() {
        repository.getAllClassRoom()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<EntityClassRoom>>() {
                    @Override
                    public void accept(List<EntityClassRoom> classRoomList) throws Exception {
                        view.updateClassRooms(classRoomList);
                    }
                });


    }

    @Override
    public void updateStudents() {
        repository.getAllStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Student>>() {
                    @Override
                    public void accept(List<Student> students) throws Exception {
                        view.updateStudents(students);

                    }
                });


    }


    @Override
    public void openDetailsStudentFragment(Student student) {
        view.openDetailsStudentFragment(student);

    }

    @Override
    public void openStudentsListFragment(EntityClassRoom classRoom) {
        view.openClassRoomDetailsFragment(classRoom);
    }


}
