package com.example.schooldatabaseapp.classRoom.mvvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.schooldatabaseapp.room.repository.RoomClassRoomRepository;

public class ViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {
    public ViewModelFactory(@NonNull Application application) {
            super(application);
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class < T > modelClass) {
            if (modelClass.isAssignableFrom(ClassRoomListViewModel.class)) {
                return (T) new ClassRoomListViewModel(
                        RoomClassRoomRepository.getInstance()
                );
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }


}