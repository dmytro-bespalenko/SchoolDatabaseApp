package com.example.schooldatabaseapp.classRoom;

import android.os.Bundle;
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
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.DatabaseClassRoomRepository;
import com.example.schooldatabaseapp.view.ClassRoomsRecyclerAdapter;
import com.example.schooldatabaseapp.view.FragmentChangeListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ClassRoomsRecyclerFragment extends Fragment implements ClassRoomContract.View {


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

        DatabaseClassRoomRepository repository = new DatabaseClassRoomRepository(view.getContext());
        repository.open();

        presenter = new ClassRoomPresenter(this, repository);

        FloatingActionButton button = view.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOtherFragment();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.classrooms_recycle_view);
        recyclerAdapter = new ClassRoomsRecyclerAdapter(classRoomList);
        recyclerAdapter.registerListener(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(recyclerAdapter);

    }

    public void showOtherFragment() {
        Fragment fr = new AddClassRoomFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        Objects.requireNonNull(fc).replaceFragment(fr);

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
        classRoomList.remove(position);
        recyclerAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.updateClassRooms();

    }
}