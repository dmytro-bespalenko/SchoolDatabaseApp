package com.example.schooldatabaseapp.students;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class StudentsListPresenter implements StudentsListContract.Presenter {

    private static final String TAG = "My_tag";
    private StudentsRepository repository;
    private StudentsListContract.View view;

    public StudentsListPresenter(StudentsListContract.View callBack) {
        this.repository = DatabaseStudentsRepository.getInstance();
        this.view = callBack;
    }

    @SuppressLint("CheckResult")

    @Override
    public void updateStudent() {

        repository.getAllStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(students -> view.updateStudents(students));

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
        repository.delete(studentsList.get(adapterPosition).getId())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public void openDetailsStudentFragment(Student student) {
        view.openStudentsDetailsFragment(student);
    }
}
