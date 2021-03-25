package com.example.schooldatabaseapp.classRoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.addClass.AddClassRoomFragment;
import com.example.schooldatabaseapp.addStudents.AddStudentFragment;
import com.example.schooldatabaseapp.students.StudentsListFragment;
import com.example.schooldatabaseapp.editClassRoom.EditClassRoomFragment;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.adapters.ClassRoomsRecyclerAdapter;
import com.example.schooldatabaseapp.base.FragmentChangeListener;

import java.util.ArrayList;
import java.util.List;


public class ClassRoomsListFragment extends Fragment implements ClassRoomListContract.View {


    private static final String TAG = "My_Tag";
    private ClassRoomsRecyclerAdapter recyclerAdapter;
    private List<ClassRoom> classRoomList = new ArrayList<>();
    private ClassRoomListContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_rooms_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ClassRoomListPresenter(this, requireContext());

        Button addClassButton = view.findViewById(R.id.addClassButton);
        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showAddClassFragment();
            }
        });

        Button addStudentButton = view.findViewById(R.id.addStudentButton);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showAddStudentFragment();
            }
        });


        RecyclerView recyclerView = view.findViewById(R.id.classrooms_recycle_view);
        recyclerAdapter = new ClassRoomsRecyclerAdapter(classRoomList);
        recyclerAdapter.registerClassRoomsListener(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(recyclerAdapter);

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
    public void openClassRoomEditFragment(ClassRoom classRoom) {
        Fragment fragment = new EditClassRoomFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pos", classRoom);
        fragment.setArguments(bundle);
        fc.replaceFragment(fragment);
    }

    @Override
    public void openClassRoomDetailsFragment(ClassRoom classRoom) {
        Fragment fragment = new StudentsListFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pos", classRoom);
        fragment.setArguments(bundle);
        fc.replaceFragment(fragment);
    }

    @Override
    public void openAddClassRoomFragment() {
        Fragment fragment = new AddClassRoomFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fragment);
    }

    @Override
    public void openAddStudentFragment() {

        Fragment fragment = new AddStudentFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
        fragmentChangeListener.replaceFragment(fragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.updateClassRooms();

    }
}