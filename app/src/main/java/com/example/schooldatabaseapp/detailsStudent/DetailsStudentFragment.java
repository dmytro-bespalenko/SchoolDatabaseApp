package com.example.schooldatabaseapp.detailsStudent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.base.FragmentChangeListener;
import com.example.schooldatabaseapp.editStudent.EditStudentFragment;
import com.example.schooldatabaseapp.model.Student;

import java.util.Objects;

public class DetailsStudentFragment extends Fragment implements DetailsStudentContract.View {


    private TextView idView;
    private TextView firstNameView;
    private TextView lastNameView;
    private TextView genderView;
    private TextView ageView;
    private Student student;
    private Button editStudent;

    private DetailsStudentContract.Presenter presenter;


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

        editStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showOtherFragment();
            }
        });


    }



    @Override
    public void openOtherFragment() {
        Fragment fragment = new EditStudentFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        Objects.requireNonNull(fc).replaceFragment(fragment);
    }
}