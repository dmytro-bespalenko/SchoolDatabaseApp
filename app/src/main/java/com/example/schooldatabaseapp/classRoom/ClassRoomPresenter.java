package com.example.schooldatabaseapp.classRoom;

import com.example.schooldatabaseapp.model.ClassRoomRepository;

public class ClassRoomPresenter implements ClassRoomContract.Presenter {

    private final ClassRoomContract.View view;
    private final ClassRoomRepository classRoomRepository;

    public ClassRoomPresenter(ClassRoomContract.View mView, ClassRoomRepository repository) {
        this.view = mView;
        this.classRoomRepository = repository;
    }


    @Override
    public void updateClassRooms() {
        view.updateRooms(classRoomRepository.getAll());
    }

    @Override
    public void onItemWasLongClick(int adapterPosition) {

        view.deleteClassRoom(classRoomRepository.delete(adapterPosition));
    }


}
