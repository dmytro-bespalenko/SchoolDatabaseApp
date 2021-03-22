package com.example.schooldatabaseapp.addStudents;

import android.content.Context;

import com.example.schooldatabaseapp.dbase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.Student;

public class AddStudentPresenter implements AddStudentContract.Presenter {


    private DatabaseStudentsRepository repository;
    private AddStudentContract.View view;

    public AddStudentPresenter(AddStudentContract.View view, Context context) {

        this.view = view;
        repository = new DatabaseStudentsRepository(context);

    }

    @Override
    public void addNewStudent(String firstName, String lastName, int age) {

        repository.insert(new Student(firstName, lastName, 0, "Male", age));

    }
}
