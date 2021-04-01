package com.example.schooldatabaseapp.students;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class StudentsListPresenter implements StudentsListContract.Presenter {

    private static final String TAG = "My_tag";
    private StudentsRepository repository;
    private StudentsListContract.View view;
    private Handler handler;

    public StudentsListPresenter(StudentsListContract.View callBack) {
        this.repository = DatabaseStudentsRepository.getInstance();
        this.view = callBack;
    }

    @SuppressLint("CheckResult")

    @Override
    public void updateStudent() {

        view.updateStudents(repository.getAll());

//        repository.getAll()
//                .subscribeOn(Schedulers.io())
//                .map(new Function<List<Student>, List<Student>>() {
//                         @Override
//                         public List<Student> apply(@NonNull List<Student> students) throws Exception {
//
//                             return new ArrayList<>(students);
//                         }
//                     }
//
//                ).observeOn(AndroidSchedulers.mainThread())
//                .subscribe();

        Log.d(TAG, "Handler updateStudent : " + Thread.currentThread().getName());


    }

//    @SuppressLint("CheckResult")
//    @Override
//    public List<ClassRoom> getClassRooms() {
//
//        List<ClassRoom> resultList = new ArrayList<>();
//        Observable.fromArray(repository.getAllClassRoom())
//                .subscribeOn(Schedulers.io())
//                .flatMap(new Function<List<ClassRoom>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(@NonNull List<ClassRoom> classRoomList) throws Exception {
//                        resultList.addAll(classRoomList);
//                        return (ObservableSource<?>) classRoomList;
//                    }
//                }).observeOn(AndroidSchedulers.mainThread());
//
//        return resultList;
//    }


    @Override
    public void openAddStudentFragment() {
        view.openAddStudentFragment();
    }

    @Override
    public void deleteStudent(List<Student> studentsList, int adapterPosition) {
        repository.delete(studentsList.get(adapterPosition).getId());
    }

    @Override
    public void openDetailsStudentFragment(Student student) {
        view.openStudentsDetailsFragment(student);
    }
}
