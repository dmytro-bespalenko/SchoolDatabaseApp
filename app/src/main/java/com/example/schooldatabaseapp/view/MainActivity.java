package com.example.schooldatabaseapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.classRoom.ClassRoomsRecyclerFragment;

public class MainActivity extends AppCompatActivity  {

    private ClassRoomsRecyclerFragment fragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new ClassRoomsRecyclerFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.classrooms_container, fragment).commit();



    }




}