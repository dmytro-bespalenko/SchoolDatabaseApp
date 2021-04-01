package com.example.schooldatabaseapp.detailsStudent;

import com.example.schooldatabaseapp.dataBase.DatabaseStudentsRepository;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DetailsStudentPresenter implements DetailsStudentContract.Presenter {

    private static final String TAG = "My_tag";
    private DetailsStudentContract.View view;
    private DatabaseStudentsRepository repository;
    private Executor executor;

    public DetailsStudentPresenter(DetailsStudentContract.View callBack) {
        this.view = callBack;
        repository = DatabaseStudentsRepository.getInstance();
    }

    @Override
    public void openEditStudentFragment() {

        view.openEditStudentFragment();

    }

    @Override
    public void deleteStudent(Student student) {
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    view.deleteCurrentStudent(repository.getAll(), repository.delete(student.getId()));
                } catch (IndexOutOfBoundsException ignored) {

                }
            }
        });

    }

    @Override
    public List<ClassRoom> getAllClassRooms() {
        return repository.getAllClassRoom();
    }


}
