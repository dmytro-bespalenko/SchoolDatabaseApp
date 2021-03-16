package com.example.schooldatabaseapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.schooldatabaseapp.R;

public class MainActivity extends AppCompatActivity  {

    private ClassRoomsRecyclerFragment fragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new ClassRoomsRecyclerFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.classrooms_container, fragment).commit();

//        databaseRepository = new DatabaseRepository(this);
//        presenter = new ClassRoomPresenter(this, databaseRepository);



    }




}