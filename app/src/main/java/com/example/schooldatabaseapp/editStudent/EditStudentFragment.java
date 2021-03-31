package com.example.schooldatabaseapp.editStudent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.detailsStudent.DetailsStudentContract;
import com.example.schooldatabaseapp.detailsStudent.DetailsStudentFragment;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.students.StudentsListFragment;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class EditStudentFragment extends Fragment implements EditStudentContract.View {


    private static final String TAG = "My_Tag";
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editAge;
    private Spinner editGenderSpinner;
    private Spinner editClassSpinner;
    private Student student;
    private Button saveEditButton;

    private List<ClassRoom> classRoomList = new ArrayList<>();

    private EditStudentContract.Presenter presenter;
    private String selectedGender;
    private int selectedClassId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_student, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new EditStudentPresenter(this, view.getContext());

        editFirstName = view.findViewById(R.id.edit_student_first_name);
        editLastName = view.findViewById(R.id.edit_student_last_name);
        editAge = view.findViewById(R.id.edit_student_age);
        editGenderSpinner = view.findViewById(R.id.edit_spinner_Gender);
        editClassSpinner = view.findViewById(R.id.edit_className_spinner);
        saveEditButton = view.findViewById(R.id.save_edit_student);
        classRoomList = presenter.getClassRooms();


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            student = bundle.getParcelable("pos");
            editFirstName.setText(student.getFirstName());
            editLastName.setText(student.getLastName());
            editAge.setText(String.valueOf(student.getAge()));

        }

        getClassRoomSpinnerItems();
        setEditGenderSpinner();

        saveEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEditFields();
            }
        });

    }

    private void setEditGenderSpinner() {

        String compareValue = student.getGender();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editGenderSpinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            editGenderSpinner.setSelection(spinnerPosition);
        }
        editGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String[] choose = getResources().getStringArray(R.array.gender);
                selectedGender = choose[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getClassRoomSpinnerItems() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item);
        int spinnerPosition = 0;

        for (int i = 0; i < classRoomList.size(); i++) {
            adapter.add((classRoomList.get(i).getClassName()));
            Log.d(TAG, "id_class: " + classRoomList.get(i).getClassId());
        }
        editClassSpinner.setAdapter(adapter);

        for (int i = 0; i < classRoomList.size(); i++) {
            if (classRoomList.get(i).getClassId() == student.getClassId()) {
                spinnerPosition = adapter.getPosition(classRoomList.get(i).getClassName());
            }
        }

        if (student != null) {
            selectedClassId = adapter.getPosition(classRoomList.get(spinnerPosition).getClassName());
        } else {
            selectedClassId = 0;
        }

        editClassSpinner.setSelection(selectedClassId, true);
        editClassSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedClassId = classRoomList.get(position).getClassId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "onNothingSelected: ");
            }
        });

    }

    public void validateEditFields() {
        if (editFirstName.getText().toString().length() == 0) {
            editFirstName.setError("First name is required!");
        } else if (editLastName.getText().toString().length() == 0) {
            editLastName.setError("Last name is required!");
        } else if (editAge.getText().toString().length() == 0) {
            editAge.setError("Age is required!");
        } else {
            String firstName = String.valueOf(editFirstName.getText());
            String lastName = String.valueOf(editLastName.getText());
            int age = Integer.parseInt(editAge.getText().toString());
            int finalSpinnerClassroomPosition = classRoomList.get(selectedClassId).getClassId();
            presenter.saveEditStudent(new Student(student.getId(), firstName, lastName, finalSpinnerClassroomPosition, selectedGender, age));

            assert getFragmentManager() != null;
            getFragmentManager().popBackStack();
            getFragmentManager().popBackStack();

        }
    }
}