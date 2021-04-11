package com.example.schooldatabaseapp.addClass;

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
import com.example.schooldatabaseapp.model.RoomClassRoomRepository;

public class AddClassRoomFragment extends Fragment implements AddClassRoomContract.View {

    private EditText editClassName;
    private EditText editClassNumber;
    private EditText editFloor;
    private Button saveButton;
    private AddClassRoomContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_class_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new AddClassRoomPresenter(this, RoomClassRoomRepository.getInstance());
        editClassName = view.findViewById(R.id.addClassName);
        editClassNumber = view.findViewById(R.id.addClassNumber);
        editFloor = view.findViewById(R.id.addFloor);
        saveButton = view.findViewById(R.id.saveButton);

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
        } else if (editClassNumber.getText().toString().length() == 0) {
            editClassNumber.setError("Class number is required!");
        } else if (editFloor.getText().toString().length() == 0) {
            editFloor.setError("Floor is required!");
        } else {
            String className = String.valueOf(editClassName.getText());
            int classNumber = Integer.parseInt(editClassNumber.getText().toString());
            int floor = Integer.parseInt(editFloor.getText().toString());
            presenter.addNewClassRoom(className, classNumber, floor);
            assert getFragmentManager() != null;
            getFragmentManager().popBackStack();
        }
    }
}