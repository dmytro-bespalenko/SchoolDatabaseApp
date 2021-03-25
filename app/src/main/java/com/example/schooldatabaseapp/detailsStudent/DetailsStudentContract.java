package com.example.schooldatabaseapp.detailsStudent;

import com.example.schooldatabaseapp.model.Student;

public interface DetailsStudentContract {

    interface View {

        void openEditStudentFragment();
    }

    interface Presenter {
        void openEditStudentFragment();
    }

}
