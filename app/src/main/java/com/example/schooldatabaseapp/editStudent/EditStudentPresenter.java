package com.example.schooldatabaseapp.editStudent;

import android.content.Context;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

import java.util.List;

public class EditStudentPresenter implements EditStudentContract.Presenter {

    private EditStudentContract.View view;
    private StudentsRepository studentsRepository;

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
        studentsRepository.update(student);

    }
}
