package com.example.schooldatabaseapp.classRoom;

import android.content.Context;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.dataBase.DatabaseClassRoomRepository;

import java.util.List;

public class ClassRoomListPresenter implements ClassRoomListContract.Presenter {

    private final ClassRoomListContract.View view;
    private final ClassRoomRepository repository;

    public ClassRoomListPresenter(ClassRoomListContract.View callBack, Context context) {
        this.repository = new DatabaseClassRoomRepository(context);
        this.view = callBack;
    }


    @Override
    public void updateClassRooms() {
        view.updateRooms(repository.getAll());
    }

    @Override
    public void onItemWasLongClick(List<ClassRoom> all, int adapterPosition) {

        view.deleteClassRoom(repository.delete(all.get(adapterPosition).getClassId()));

    }

    @Override
    public void openEditFragment(ClassRoom classRoom) {
        view.openClassRoomEditFragment(classRoom);
    }

    @Override
    public void onItemClickListener(ClassRoom classRoom) {
        view.openClassRoomDetailsFragment(classRoom);

    }

    @Override
    public void showAddClassFragment() {
        view.openAddClassRoomFragment();
    }

    @Override
    public void showAddStudentFragment() {
        view.openAddStudentFragment();
    }


}
