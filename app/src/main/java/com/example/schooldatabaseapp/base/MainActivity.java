package com.example.schooldatabaseapp.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.classRoom.ClassRoomsListFragment;

public class MainActivity extends AppCompatActivity implements FragmentChangeListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassRoomsListFragment classRoomsListFragment = new ClassRoomsListFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.classrooms_container, classRoomsListFragment).commit();


    }


    @Override
    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.classrooms_container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();

    }
}