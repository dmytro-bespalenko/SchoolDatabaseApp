package com.example.schooldatabaseapp.addStudents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.schooldatabaseapp.R;

public class AddStudentFragment extends Fragment implements AddStudentContract.View {


    private EditText editFirstName;
    private EditText editLastName;
    private EditText editaAge;
    private Button saveStudent;

    private AddStudentContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_student, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new AddStudentPresenter(this, view.getContext());
        editFirstName = view.findViewById(R.id.edit_firstName);
        editLastName = view.findViewById(R.id.edit_lastName);
        editaAge = view.findViewById(R.id.edit_age);

        saveStudent = view.findViewById(R.id.save_Student_button);
        saveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEditFields();

            }
        });


    }
    public void validateEditFields() {
        if (editFirstName.getText().toString().length() == 0) {
            editFirstName.setError("First name is required!");
        } else if (editLastName.getText().toString().length() == 0) {
            editLastName.setError("Last name is required!");
        } else if (editaAge.getText().toString().length() == 0) {
            editaAge.setError("Age is required!");
        } else {
            String firstName = String.valueOf(editFirstName.getText());
            String lastName = String.valueOf(editLastName.getText());
            int age = Integer.parseInt(editaAge.getText().toString());
            presenter.addNewStudent(firstName, lastName, age);
            assert getFragmentManager() != null;
            getFragmentManager().popBackStack();
        }
    }

}