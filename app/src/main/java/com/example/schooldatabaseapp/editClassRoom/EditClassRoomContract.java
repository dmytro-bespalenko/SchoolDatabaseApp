package com.example.schooldatabaseapp.editClassRoom;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.room.entity.EntityClassRoom;

public interface EditClassRoomContract {

    interface View {

    }

    interface Presenter {
        void editClassRoom(ClassRoom classRoom);

    }
}
