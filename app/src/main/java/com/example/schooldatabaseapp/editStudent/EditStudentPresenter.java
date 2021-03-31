package com.example.schooldatabaseapp.editStudent;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.List;

public class EditStudentPresenter implements EditStudentContract.Presenter {

    private static final String TAG = "My_tag";
    private EditStudentContract.View view;
    private StudentsRepository studentsRepository;
    private Handler handler;

    public EditStudentPresenter(EditStudentContract.View callBack, Context context) {
        this.view = callBack;
        this.studentsRepository = new DatabaseStudentsRepository(context);
    }

    @Override
    public List<ClassRoom> getClassRooms() {
        return studentsRepository.getAllClassRoom();
    }

    @Override
    public void saveEditStudent(Student student) {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                studentsRepository.update(student);
                Log.d(TAG, "run: " + Thread.currentThread().getName() + " HandlerUpdateStudent " + getClass().getName());

            }
        });

    }
}
