package com.example.schooldatabaseapp.detailsStudent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.base.FragmentChangeListener;
import com.example.schooldatabaseapp.editStudent.EditStudentFragment;
import com.example.schooldatabaseapp.model.Student;

import static android.app.Activity.RESULT_OK;

public class DetailsStudentFragment extends Fragment implements DetailsStudentContract.View {


    private TextView idView;
    private TextView firstNameView;
    private TextView lastNameView;
    private TextView genderView;
    private TextView ageView;
    private Student student;
    private Button editStudent;
    private int count;

    private DetailsStudentContract.Presenter presenter;
    private Student student2;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new DetailsStudentPresenter(this, view.getContext());

        idView = view.findViewById(R.id.student_id);
        firstNameView = view.findViewById(R.id.student_first_name);
        lastNameView = view.findViewById(R.id.student_last_name);
        genderView = view.findViewById(R.id.student_gender);
        ageView = view.findViewById(R.id.student_age);
        editStudent = view.findViewById(R.id.edit_student);

        Bundle bundle = this.getArguments();


        if (bundle != null) {
            student = bundle.getParcelable("pos");

            idView.setText(String.valueOf(student.getId()));
            firstNameView.setText(student.getFirstName());
            lastNameView.setText(student.getLastName());
            genderView.setText(student.getGender());
            ageView.setText(String.valueOf(student.getAge()));


        }
//
//        if (bundle.containsKey("sear")){
//            student2 = bundle.getParcelable("sear");
//            idView.setText(String.valueOf(student2.getId()));
//            firstNameView.setText(student2.getFirstName());
//            lastNameView.setText(student2.getLastName());
//            genderView.setText(student2.getGender());
//            ageView.setText(String.valueOf(student2.getAge()));
//        }


        editStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openEditStudentFragment();
            }
        });


    }


    @Override
    public void openEditStudentFragment() {
        Fragment fragment = new EditStudentFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pos", student);
        fragment.setArguments(bundle);
        fragmentChangeListener.replaceFragment(fragment);

    }
}