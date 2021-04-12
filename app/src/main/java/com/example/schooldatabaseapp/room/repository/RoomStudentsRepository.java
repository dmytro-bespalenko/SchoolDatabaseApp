package com.example.schooldatabaseapp.room.repository;

import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.model.StudentsRepository;
import com.example.schooldatabaseapp.room.dao.StudentsDao;
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

public class RoomStudentsRepository implements StudentsRepository {

    private static RoomStudentsRepository instance;
    private final StudentsDao dao;

    public RoomStudentsRepository(StudentsDao dao) {
        this.dao = dao;
    }

    public static synchronized RoomStudentsRepository getInstance() {
        if (instance == null) {
            throw new RuntimeException();
        }
        return instance;
    }

    public static void initInstance(StudentsDao studentDao) {
        if (studentDao != null) {
            instance = new RoomStudentsRepository(studentDao);
        }
    }


    @Override
    public Single<List<Student>> getAllStudents() {
        return dao.getAllStudents()
                .map(new Function<List<EntityStudent>, List<Student>>() {
                    @Override
                    public List<Student> apply(@NonNull List<EntityStudent> entityStudents) throws Exception {
                        List<Student> studentList = new ArrayList<>();
                        for (int i = 0; i < entityStudents.size(); i++) {
                            studentList.add(new Student(entityStudents.get(i).getId()
                                    , entityStudents.get(i).getFirstName()
                                    , entityStudents.get(i).getLastName()
                                    , entityStudents.get(i).getClassId()
                                    , entityStudents.get(i).getGender()
                                    , entityStudents.get(i).getAge()
                            ));
                        }

                        return studentList;
                    }
                })
                ;
    }

    @Override
    public Single<List<ClassRoom>> getAllClassRoom() {
        return dao.getAllClassRoom()
                .map(new Function<List<EntityClassRoom>, List<ClassRoom>>() {
                    @Override
                    public List<ClassRoom> apply(@NonNull List<EntityClassRoom> entityClassRooms) throws Exception {
                        List<ClassRoom> classRoomList = new ArrayList<>();
                        for (int i = 0; i < entityClassRooms.size(); i++) {

                            classRoomList.add(new ClassRoom(entityClassRooms.get(i).getId()
                                    , entityClassRooms.get(i).getClassName()
                                    , entityClassRooms.get(i).getClassNumber()
                                    , entityClassRooms.get(i).getStudentsCount()
                                    , entityClassRooms.get(i).getFloor()
                            ));
                        }

                        return classRoomList;
                    }
                })
                ;
    }

    @Override
    public Completable insert(Student student) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                dao.insert(new EntityStudent(student.getFirstName(), student.getLastName(), student.getClassId(), student.getGender(), student.getAge()));
            }
        });
    }

    @Override
    public Completable delete(int studentId) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                try {
                    dao.delete(studentId);

                } catch (Exception e) {
                    emitter.onError(e);
                } finally {

                    emitter.onComplete();
                }

            }
        });
    }

    @Override
    public Completable update(Student student) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                try {
                    dao.update(new EntityStudent(student.getId(), student.getFirstName(), student.getLastName(), student.getClassId(), student.getGender(), student.getAge()));
                } catch (Exception e) {
                    emitter.onError(e);
                } finally {

                    emitter.onComplete();
                }
            }
        });
    }


}
