package com.example.schooldatabaseapp.searchBy;

import android.annotation.SuppressLint;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")


public class SearchByPresenter implements SearchByContract.Presenter {

    private SearchByContract.View view;
    private StudentsRepository repository;

    public SearchByPresenter(SearchByContract.View callBack) {
        this.view = callBack;
        repository = DatabaseStudentsRepository.getInstance();
    }

    @Override
    public void updateClassRooms() {
        view.updateClassRooms(repository.getAllClassRoom());

    }

    @Override
    public void updateStudents() {
//        repository.getAll()
//                .subscribeOn(Schedulers.io())
//                .map(new Function<List<Student>, List<Student>>() {
//                    @Override
//                    public List<Student> apply(@NonNull List<Student> students) throws Exception {
//                        return students;
//                    }
//                }).observeOn(AndroidSchedulers.mainThread())
//                .subscribe();
//
//        ;


        view.updateStudents(repository.getAll());
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
