package com.example.schooldatabaseapp.classRoom;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.DatabaseRepository;
import com.example.schooldatabaseapp.addClass.AddClassRoomActivity;
import com.example.schooldatabaseapp.view.ClassRoomsRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class ClassRoomsRecyclerFragment extends Fragment implements ClassRoomContract.View {


    private static final String TAG = "My_Tag";
    private ClassRoomsRecyclerAdapter recyclerAdapter;
    private List<ClassRoom> classRoomList;
    private ClassRoomContract.Repository repository;
    private ClassRoomContract.Presenter presenter;
    private FloatingActionButton button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = new DatabaseRepository(view.getContext());
        presenter = new ClassRoomPresenter(this, (DatabaseRepository) repository);
        repository.open();
        classRoomList = repository.getClassRooms();
        button = view.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddClassRoomActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.classrooms_recycle_view);
        recyclerAdapter = new ClassRoomsRecyclerAdapter(classRoomList);
        recyclerAdapter.registerListener(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_rooms_recycler, container, false);
    }


    @Override
    public void onActivityClickBack(int classRoom) {

    }

}