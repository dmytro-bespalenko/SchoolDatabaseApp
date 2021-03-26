package com.example.schooldatabaseapp.searchBy;

import android.content.Context;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;

public class SearchByPresenter implements SearchByContract.Presenter {

    private SearchByContract.View view;
    private StudentsRepository repository;

    public SearchByPresenter(SearchByContract.View callBack, Context context) {
        this.view = callBack;
        repository = new DatabaseStudentsRepository(context);
    }

    @Override
    public void updateClassRooms() {
        view.updateClassRooms(repository.getAllClassRoom());
    }

    @Override
    public void updateStudents() {
            view.updateStudents(repository.getAll());
    }



    @Override
    public void onItemStudentClickListener(Student student) {
        view.openStudentsDetailsFragment(student);
    }

    @Override
    public void onItemClassRoomClickListener(ClassRoom classRoom) {
            view.openClassRoomDetailsFragment(classRoom);
    }


}
