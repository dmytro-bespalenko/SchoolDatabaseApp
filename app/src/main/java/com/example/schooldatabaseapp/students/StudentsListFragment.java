package com.example.schooldatabaseapp.students;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.addStudents.AddStudentFragment;
import com.example.schooldatabaseapp.detailsStudent.DetailsStudentFragment;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.base.FragmentChangeListener;
import com.example.schooldatabaseapp.adapters.StudentsRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class StudentsListFragment extends Fragment implements StudentsListContract.View {


    private static final String TAG = "My_Tag";
    private StudentsRecyclerAdapter recyclerAdapter;
    private List<Student> studentList = new ArrayList<>();
    private StudentsListContract.Presenter presenter;


    private TextView className;
    private TextView classNumber;
    private TextView studentsCount;
    private TextView floor;
    private FloatingActionButton addButton;
    int count;
    private ClassRoom classRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_room_details, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new StudentsListPresenter(this, requireContext());

        RecyclerView recyclerView = view.findViewById(R.id.students_recycle_view);
        recyclerAdapter = new StudentsRecyclerAdapter(studentList);
        recyclerAdapter.registerStudentsListener(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(recyclerAdapter);

        className = view.findViewById(R.id.classNameInfo);
        classNumber = view.findViewById(R.id.classNumberInfo);
        studentsCount = view.findViewById(R.id.studentsCountInfo);
        floor = view.findViewById(R.id.floorInfo);
        addButton = view.findViewById(R.id.add_student_from_class_details);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openAddStudentFragment();
            }
        });


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            classRoom = bundle.getParcelable("pos");

            className.setText(classRoom.getClassName());
            classNumber.setText(String.valueOf(classRoom.getClassNumber()));
            studentsCount.setText(String.valueOf(count));
            floor.setText(String.valueOf(classRoom.getFloor()));
        }


    }


    @Override
    public void updateStudents(List<Student> all) {
        studentList.clear();

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getClassId() == classRoom.getClassId()) {
                studentList.add(all.get(i));
            }
        }
        count = studentList.size();
        studentsCount.setText(String.valueOf(count));
        recyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void deleteStudent(int position) {
        if (position == studentList.size()) {
            position = 0;
        }
        int finalPosition = position;
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked

                        studentList.remove(finalPosition);

                        presenter.updateStudent();
                        recyclerAdapter.notifyItemRemoved(finalPosition);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Delete student?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }


    @Override
    public void openStudentsDetailsFragment(Student student) {

        Fragment fragment = new DetailsStudentFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
        Bundle bundle = new Bundle();
        bundle.putInt("count", studentList.size());
        bundle.putParcelable("pos", student);
        fragment.setArguments(bundle);
        fragmentChangeListener.replaceFragment(fragment);
    }

    @Override
    public void openAddStudentFragment() {

        Fragment fragment = new AddStudentFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
        fragmentChangeListener.replaceFragment(fragment);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.updateStudent();
    }
}