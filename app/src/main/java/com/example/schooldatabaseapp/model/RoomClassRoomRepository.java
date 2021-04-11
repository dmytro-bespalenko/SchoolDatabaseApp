package com.example.schooldatabaseapp.model;

import com.example.schooldatabaseapp.base.SchoolApplication;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class RoomClassRoomRepository implements ClassRoomRepository {

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
                            classRooms.add(new ClassRoom(entityClassRooms.get(i).getClassName(),
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
    public Single<List<Student>> getAllStudents() {
        return dao.getAllStudents()
                .map(new Function<List<EntityStudent>, List<Student>>() {
                    @Override
                    public List<Student> apply(@NonNull List<EntityStudent> entityStudents) throws Exception {
                        List<Student> students = new ArrayList<>();
                        for (int i = 0; i < entityStudents.size(); i++) {
                            students.add(new Student(entityStudents.get(i).getFirstName()
                                    , entityStudents.get(i).getLastName()
                                    , entityStudents.get(i).getClassId()
                                    , entityStudents.get(i).getGender()
                                    , entityStudents.get(i).getAge()
                            ));
                        }

                        return students;
                    }
                })
                ;
    }

    @Override
    public Single<Long> insert(ClassRoom classRoom) {
        return dao.insert(new EntityClassRoom(classRoom.getClassName()
                , classRoom.getClassNumber()
                , classRoom.getStudentsCount()
                , classRoom.getFloor()
        ));
    }

    @Override
    public Completable delete(int classId) {
        return dao.delete(classId);
    }

    @Override
    public Completable deleteStudent(int studentId) {
        return dao.deleteStudent(studentId);
    }

    @Override
    public Completable update(ClassRoom classRoom) {
        return null;
    }
}
