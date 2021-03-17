package com.example.schooldatabaseapp.addClass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.model.DatabaseClassRoomRepository;

import java.util.Objects;

public class AddClassRoomFragment extends Fragment {

    private EditText editClassName;
    private EditText editClassNumber;
    private EditText editFloor;
    private AddClassRoomContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_class_room, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseClassRoomRepository repository = new DatabaseClassRoomRepository(Objects.requireNonNull(getContext()));
        repository.open();

        presenter = new AddClassRoomPresenter(repository);
        editClassName = view.findViewById(R.id.editClassName);
        editClassNumber = view.findViewById(R.id.editClassNumber);
        editFloor = view.findViewById(R.id.editFloor);
        Button saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEditFields();
            }
        });
    }

    public void validateEditFields() {
        if (editClassName.getText().toString().length() == 0) {
            editClassName.setError("Class name is required!");
        }
        if (editClassNumber.getText().toString().length() == 0) {
            editClassNumber.setError("Class number is required!");
        }
        if (editFloor.getText().toString().length() == 0) {
            editFloor.setError("Floor is required!");
        } else {
            String className = String.valueOf(editClassName.getText());
            int classNumber = Integer.parseInt(editClassNumber.getText().toString());
            int floor = Integer.parseInt(editFloor.getText().toString());
            presenter.addNewClassRoom(className, classNumber, floor);
        }
    }
}