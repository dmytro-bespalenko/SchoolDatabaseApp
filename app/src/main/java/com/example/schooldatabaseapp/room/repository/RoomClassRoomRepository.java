package com.example.schooldatabaseapp.room.repository;

import android.util.Log;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.room.dao.ClassRoomDao;
import com.example.schooldatabaseapp.room.entity.EntityClassRoom;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class RoomClassRoomRepository implements ClassRoomRepository {

    private static final String TAG = "TAG";
    private static RoomClassRoomRepository instance;
    private final ClassRoomDao dao;

    public RoomClassRoomRepository(ClassRoomDao dao) {
        this.dao = dao;
    }


    public static synchronized RoomClassRoomRepository getInstance() {
        if (instance == null) {
            throw new RuntimeException();
        }
        return instance;
    }

    public static void initInstance(ClassRoomDao classRoomDao) {
        if (classRoomDao != null) {
            instance = new RoomClassRoomRepository(classRoomDao);
        }
    }


    @Override
    public Single<List<ClassRoom>> getAllClassrooms() {
        return dao.getAllClassrooms()
                .map(new Function<List<EntityClassRoom>, List<ClassRoom>>() {
                    @Override
                    public List<ClassRoom> apply(@NonNull List<EntityClassRoom> entityClassRooms) throws Exception {
                        List<ClassRoom> classRooms = new ArrayList<>();
                        for (int i = 0; i < entityClassRooms.size(); i++) {
                            classRooms.add(new ClassRoom(entityClassRooms.get(i).getId(),
                                    entityClassRooms.get(i).getClassName(),
                                    entityClassRooms.get(i).getClassNumber(),
                                    entityClassRooms.get(i).getStudentsCount(),
                                    entityClassRooms.get(i).getFloor()
                            ));
                        }
                        return classRooms;
                    }
                });
    }


    @Override
    public Completable insert(ClassRoom classRoom) {
        Log.d(TAG, "insertRoom: " + Thread.currentThread().getName());
        return dao.insert(new EntityClassRoom(classRoom.getClassName(), classRoom.getClassNumber(), classRoom.getStudentsCount(), classRoom.getFloor()));
    }


    @Override
    public Completable delete(Integer classId) {

        return dao.delete(classId);
    }


    @Override
    public Completable update(ClassRoom classRoom) {
        return dao.update(new EntityClassRoom(classRoom.getClassId(), classRoom.getClassName(),
                classRoom.getClassNumber(), classRoom.getStudentsCount(), classRoom.getFloor()));

    }
}
