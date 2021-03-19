package com.example.schooldatabaseapp.classRoom;

import android.content.Context;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.model.DatabaseClassRoomRepository;

import java.util.List;

public class ClassRoomPresenter implements ClassRoomContract.Presenter {

    private final ClassRoomContract.View view;
    private final ClassRoomRepository repository;

    public ClassRoomPresenter(ClassRoomContract.View view, Context context) {
        this.repository = new DatabaseClassRoomRepository(context);
        this.view = view;
    }


    @Override
    public void updateClassRooms() {
        view.updateRooms(repository.getAll());
    }

    @Override
    public void onItemWasLongClick(List<ClassRoom> all, int adapterPosition) {

        if (!all.isEmpty()) {
            view.deleteClassRoom(repository.delete(all.get(adapterPosition).getId()));
        }

    }

    @Override
    public void openEditFragment(ClassRoom classRoom) {
        view.openFragment(classRoom);
    }

    @Override
    public void onItemClickListener(ClassRoom classRoom) {
        view.openClassRoomDetailsFragment(classRoom);

    }


}
