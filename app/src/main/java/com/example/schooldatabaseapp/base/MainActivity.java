package com.example.schooldatabaseapp.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.addClass.AddClassRoomFragment;
import com.example.schooldatabaseapp.addStudents.AddStudentFragment;
import com.example.schooldatabaseapp.classRoom.ClassRoomsListFragment;
import com.example.schooldatabaseapp.classRoom.mvvvm.FragmentsChanger;
import com.example.schooldatabaseapp.editClassRoom.EditClassRoomFragment;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.students.StudentsListFragment;

public class MainActivity extends AppCompatActivity implements FragmentChangeListener, FragmentsChanger {


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


    @Override
    public void openClassRoomEditFragment(ClassRoom classRoom) {
        Fragment fragment = new EditClassRoomFragment();
        FragmentChangeListener fragmentChangeListener = this;
        Bundle bundle = new Bundle();
        bundle.putParcelable("pos", classRoom);
        fragment.setArguments(bundle);
        fragmentChangeListener.replaceFragment(fragment);
    }

    @Override
    public void openClassRoomDetailsFragment(ClassRoom classRoom) {
        Fragment fragment = new StudentsListFragment();
        FragmentChangeListener fragmentChangeListener = this;
        Bundle bundle = new Bundle();
        bundle.putParcelable("pos", classRoom);
        fragment.setArguments(bundle);
        fragmentChangeListener.replaceFragment(fragment);
    }

    @Override
    public void openAddClassRoomFragment() {
        Fragment fragment = new AddClassRoomFragment();
        FragmentChangeListener fragmentChangeListener = this;
        fragmentChangeListener.replaceFragment(fragment);
    }

    @Override
    public void openAddStudentFragment() {

        Fragment fragment = new AddStudentFragment();
        FragmentChangeListener fragmentChangeListener = this;
        fragmentChangeListener.replaceFragment(fragment);
    }

}