package com.example.schooldatabaseapp.addStudents;

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
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddStudentFragment extends Fragment implements AddStudentContract.View {


    private static final String TAG = "My_Tag";
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editaAge;
    private List<ClassRoom> classRoomList = new ArrayList<>();

    private Spinner genderSpinner;
    private Spinner classSpinner;
    private Button saveStudent;
    private String selectedGender;
    private int selectedClassId;

    private AddStudentContract.Presenter presenter;
    private ClassRoom classRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_student, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        presenter = new AddStudentPresenter(this, requireContext());
        editFirstName = view.findViewById(R.id.add_firstName);
        editLastName = view.findViewById(R.id.add_lastName);
        editaAge = view.findViewById(R.id.add_age);
        genderSpinner = view.findViewById(R.id.add_spinner_Gender);
        classRoomList = presenter.getClassRooms();
        saveStudent = view.findViewById(R.id.save_Student_button);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            classRoom = bundle.getParcelable("pos");
        }

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choose = getResources().getStringArray(R.array.gender);
                selectedGender = choose[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        classSpinner = view.findViewById(R.id.add_className_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item);

        for (int i = 0; i < classRoomList.size(); i++) {
            adapter.add((classRoomList.get(i).getClassName()));
            Log.d(TAG, "id_class: " + classRoomList.get(i).getClassId());
        }
        classSpinner.setAdapter(adapter);

        if (classRoom != null) {
            selectedClassId = classRoom.getClassId();
            classSpinner.setSelection(adapter.getPosition(classRoom.getClassName()), true);
        }
        // TODO: 30.03.21




        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedClassId = classRoomList.get(position).getClassId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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

            presenter.addNewStudent(firstName, lastName, selectedClassId, selectedGender, age);

            //          int finalPosition = classRoomList.get(selectedClassId).getClassId();
            assert getFragmentManager() != null;
            getFragmentManager().popBackStack();
            Log.d(TAG, "run: " + Thread.currentThread().getName());
        }
    }


}