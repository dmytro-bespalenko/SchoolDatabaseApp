package com.example.schooldatabaseapp.classRoom;

import androidx.fragment.app.Fragment;

import com.example.schooldatabaseapp.addClass.AddClassRoomFragment;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public interface ClassRoomListContract {

    interface View {

        void updateRooms(List<ClassRoom> all);

        void deleteClassRoom(ClassRoom position);

        void openClassRoomEditFragment(ClassRoom classRoom);

        void openClassRoomDetailsFragment(ClassRoom classRoom);

        void openAddClassRoomFragment();

        void openAddStudentFragment();

    }

    interface Presenter {


        void updateClassRooms();

        void onItemWasLongClick(List<ClassRoom> all, int adapterPosition);


        void openEditFragment(ClassRoom classRoom);


        void onItemClickListener(ClassRoom classRoom);

        void showAddClassFragment();

        void showAddStudentFragment();

        List<Student> getAllStudents();

        void deleteClassRoom(ClassRoom finalPosition);

        void deleteStudent(Student student);

    }


}
