package com.example.schooldatabaseapp.students;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.List;

public class StudentsListPresenter implements StudentsListContract.Presenter {

    private static final String TAG = "My_tag";
    private StudentsRepository repository;
    private StudentsListContract.View view;
    private Handler handler;

    public StudentsListPresenter(StudentsListContract.View callBack, Context context) {
        this.repository = new DatabaseStudentsRepository(context);
        this.view = callBack;

    }


    @Override
    public void updateStudent() {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                view.updateStudents(repository.getAll());
                Log.d(TAG, "Handler updateStudent : " + Thread.currentThread().getName());

            }
        });
    }

    @Override
    public List<ClassRoom> getClassRooms() {
        return repository.getAllClassRoom();
    }


    @Override
    public void onItemClickListener(Student student) {

        view.openStudentsDetailsFragment(student);

    }


    @Override
    public void onItemWasLongClick(List<Student> studentsList, int adapterPosition) {
        view.onItemDeleteWasClick(adapterPosition);
    }

    @Override
    public void openAddStudentFragment() {
        view.openAddStudentFragment();
    }

    @Override
    public void deleteStudent(List<Student> studentsList, int adapterPosition) {
        repository.delete(studentsList.get(adapterPosition).getId());
    }
}
