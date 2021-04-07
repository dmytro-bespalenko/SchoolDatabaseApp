package com.example.schooldatabaseapp.searchBy;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.repositories.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")


public class SearchByPresenter implements SearchByContract.Presenter {

    private static final String TAG = "My_Tag";
    private SearchByContract.View view;
    private StudentsRepository repository;

    public SearchByPresenter(SearchByContract.View callBack) {
        this.view = callBack;
        repository = DatabaseStudentsRepository.getInstance();
    }

    @Override
    public void updateClassRooms() {
        repository.getAllClassRoom()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ClassRoom>>() {
                    @Override
                    public void accept(List<ClassRoom> classRoomList) throws Exception {
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
    public void openStudentsListFragment(ClassRoom classRoom) {
        view.openClassRoomDetailsFragment(classRoom);
    }


}
