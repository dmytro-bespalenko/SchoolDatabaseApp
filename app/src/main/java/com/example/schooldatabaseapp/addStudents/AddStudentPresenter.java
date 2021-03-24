package com.example.schooldatabaseapp.addStudents;

import android.content.Context;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public class AddStudentPresenter implements AddStudentContract.Presenter {


    private DatabaseStudentsRepository repository;
    private AddStudentContract.View view;

    public AddStudentPresenter(AddStudentContract.View callBack, Context context) {

        this.view = callBack;
        repository = new DatabaseStudentsRepository(context);

    }

    @Override
    public void addNewStudent(String firstName, String lastName, int selectedClassId, String gender, int age) {

        repository.insert(new Student(firstName, lastName, selectedClassId, gender, age));

    }

    @Override
    public List<ClassRoom> getClassRooms() {

        return repository.getAllClassRoom();
    }



}
