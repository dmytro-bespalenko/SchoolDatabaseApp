package com.example.schooldatabaseapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.ClassRoomContract;
import com.example.schooldatabaseapp.model.DatabaseRepository;
import com.example.schooldatabaseapp.presenter.ClassRoomPresenter;

public class AddClassRoomActivity extends AppCompatActivity implements ClassRoomContract.View {

    private EditText editClassName;
    private EditText editClassNumber;
    private EditText editFloor;
    private ClassRoomContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_room);

        presenter = new ClassRoomPresenter(this, new DatabaseRepository(this));

        editClassName = findViewById(R.id.editClassName);
        editClassNumber = findViewById(R.id.editClassNumber);
        editFloor = findViewById(R.id.editFloor);
        Button saveButton = findViewById(R.id.saveButton);

//        repository.open();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.onAddButtonClicked();
                String className = String.valueOf(editClassName.getText());
                int classNumber = Integer.parseInt(editClassNumber.getText().toString());
                int floor = Integer.parseInt(editFloor.getText().toString());
                ClassRoom classRoom = new ClassRoom(5, className, classNumber, 5, floor);
//                repository.insert(classRoom);

            }
        });


    }


    @Override
    public void onActivityClickBack(int classRoom) {

    }
}