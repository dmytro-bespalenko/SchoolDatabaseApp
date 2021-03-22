package com.example.schooldatabaseapp.students;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.addStudents.AddStudentFragment;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.base.FragmentChangeListener;
import com.example.schooldatabaseapp.adapters.StudentsRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentsFragment extends Fragment implements StudentsContract.View {


    private static final String TAG = "My_Tag";
    private StudentsRecyclerAdapter recyclerAdapter;
    private List<Student> studentList = new ArrayList<>();
    private StudentsContract.Presenter presenter;


    private TextView className;
    private TextView classNumber;
    private TextView studentsCount;
    private TextView floor;
    private ClassRoom classRoom;
    private FloatingActionButton addStudentsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_room_details, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new StudentsPresenter(this, view.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.students_recycle_view);
        recyclerAdapter = new StudentsRecyclerAdapter(studentList);
        recyclerAdapter.registerStudentsListener(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(recyclerAdapter);

        className = view.findViewById(R.id.classNameInfo);
        classNumber = view.findViewById(R.id.classNumberInfo);
        studentsCount = view.findViewById(R.id.studentsCountInfo);
        floor = view.findViewById(R.id.floorInfo);
        addStudentsButton = view.findViewById(R.id.addStudentsButton);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            classRoom = bundle.getParcelable("pos");

            className.setText("Name: " + classRoom.getClassName());
            classNumber.setText("№ " + classRoom.getClassNumber());
            studentsCount.setText("Count " + classRoom.getStudentsCount());
            floor.setText("Floor " + classRoom.getFloor());
        }

        addStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOtherFragment();
            }
        });


    }

    public void showOtherFragment() {
        Fragment fragment = new AddStudentFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        Objects.requireNonNull(fc).replaceFragment(fragment);

    }

    @Override
    public void updateStudents(List<Student> all) {
        studentList.clear();
        studentList.addAll(all);
        Log.d(TAG, "updateStudents: " + studentList.size());
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteStudent(int position) {
        if (position == studentList.size()) {
            position = 0;
        }
        studentList.remove(position);
        presenter.updateStudent();
        recyclerAdapter.notifyItemRemoved(position);
    }

    @Override
    public void openFragment(Student student) {

    }

    @Override
    public void openClassRoomDetailsFragment(Student student) {

    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.updateStudent();
    }
}