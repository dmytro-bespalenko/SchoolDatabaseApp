package com.example.schooldatabaseapp.room.repository;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomRepository;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.room.dao.ClassRoomDao;
import com.example.schooldatabaseapp.room.entity.EntityClassRoom;
import com.example.schooldatabaseapp.room.entity.EntityStudent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
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
        return  dao.getAllClassrooms()
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
                });
    }

    @Override
    public Single<Long> insert(ClassRoom classRoom) {
        return Single.create(new SingleOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Long> emitter) throws Exception {
                try {
                    dao.insert(new EntityClassRoom(classRoom.getClassName()
                            , classRoom.getClassNumber()
                            , classRoom.getStudentsCount()
                            , classRoom.getFloor()));

                } catch (Exception e) {
                    emitter.onError(e);
                } finally {
                    emitter.onSuccess(1L);
                }

            }
        });
    }


    @Override
    public Completable delete(Integer classId) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                try {
                    dao.delete(classId);
                } catch (Exception e) {
                    emitter.onError(e);
                } finally {
                    emitter.onComplete();
                }
            }

        });
    }

    @Override
    public Completable deleteStudent(int studentId) {
        return null;
    }


    @Override
    public Completable update(ClassRoom classRoom) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                try {
                    dao.update(new EntityClassRoom(classRoom.getClassId(), classRoom.getClassName(), classRoom.getClassNumber(), classRoom.getStudentsCount(), classRoom.getFloor()));
                } catch (Exception e) {
                    emitter.onError(e);
                } finally {
                    emitter.onComplete();
                }
            }

        });
    }
}
