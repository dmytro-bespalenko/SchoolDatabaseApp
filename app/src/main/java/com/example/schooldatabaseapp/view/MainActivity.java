package com.example.schooldatabaseapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.schooldatabaseapp.ClassRoomContract;
import com.example.schooldatabaseapp.DatabaseRepository;
import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.presenter.ClassRoomPresenter;

public class MainActivity extends AppCompatActivity implements ClassRoomContract.View {


    private ClassRoomContract.Presenter presenter;
    private DatabaseRepository databaseRepository;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseRepository = new DatabaseRepository(this);
        presenter = new ClassRoomPresenter(this, databaseRepository);



    }


    @Override
    public void onActivityClickBack(int classRoom) {



    }
}