package com.example.schooldatabaseapp.classRoom;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.addClass.AddClassRoomFragment;
import com.example.schooldatabaseapp.editClassRoom.EditClassRoomFragment;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.view.ClassRoomsRecyclerAdapter;
import com.example.schooldatabaseapp.view.FragmentChangeListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ClassRoomsFragment extends Fragment implements ClassRoomContract.View {


    private static final String TAG = "My_Tag";
    private ClassRoomsRecyclerAdapter recyclerAdapter;
    private List<ClassRoom> classRoomList = new ArrayList<>();
    private ClassRoomContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ClassRoomPresenter(this, view.getContext());

        FloatingActionButton button = view.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOtherFragment();

            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.classrooms_recycle_view);
        recyclerAdapter = new ClassRoomsRecyclerAdapter(classRoomList);
        recyclerAdapter.registerClassRoomsListener(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(recyclerAdapter);

    }

    public void showOtherFragment() {
        Fragment fragment = new AddClassRoomFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        Objects.requireNonNull(fc).replaceFragment(fragment);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_rooms_recycler, container, false);
    }


    @Override
    public void updateRooms(List<ClassRoom> all) {
        classRoomList.clear();
        classRoomList.addAll(all);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteClassRoom(int position) {

        if (position == classRoomList.size()) {
            position = 0;
        }
        classRoomList.remove(position);
        presenter.updateClassRooms();
        recyclerAdapter.notifyItemRemoved(position);
    }

    @Override
    public void openFragment(ClassRoom classRoom) {
        Fragment fragment = new EditClassRoomFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pos",  classRoom);
        fragment.setArguments(bundle);

        Objects.requireNonNull(fc).replaceFragment(fragment);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.updateClassRooms();

    }
}