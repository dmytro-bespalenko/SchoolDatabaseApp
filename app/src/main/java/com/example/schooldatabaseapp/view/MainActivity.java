package com.example.schooldatabaseapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.addClass.AddClassRoomFragment;
import com.example.schooldatabaseapp.classRoom.ClassRoomsRecyclerFragment;

public class MainActivity extends AppCompatActivity implements FragmentChangeListener {

    private ClassRoomsRecyclerFragment classRoomsRecyclerFragment;
    private AddClassRoomFragment addClassRoomFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classRoomsRecyclerFragment = new ClassRoomsRecyclerFragment();
        addClassRoomFragment = new AddClassRoomFragment();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.classrooms_container, classRoomsRecyclerFragment).commit();


    }


    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.classrooms_container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();

    }
}