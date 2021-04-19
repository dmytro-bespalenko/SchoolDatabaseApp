package com.example.schooldatabaseapp.classRoom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.adapters.ClassRoomsRecyclerAdapter;
import com.example.schooldatabaseapp.base.FragmentChangeListener;
import com.example.schooldatabaseapp.classRoom.mvvvm.ClassRoomListViewModel;
import com.example.schooldatabaseapp.classRoom.mvvvm.FragmentsChanger;
import com.example.schooldatabaseapp.classRoom.mvvvm.ViewModelFactory;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.searchBy.SearchByFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ClassRoomsListFragment extends Fragment implements ClassRoomsRecyclerAdapter.CallBackAdapterPosition {


    private static final String TAG = "My_Tag";
    private ClassRoomsRecyclerAdapter recyclerAdapter;
    private final List<ClassRoom> classRoomList = new ArrayList<>();
    public ClassRoomListViewModel viewModel;
    public FragmentsChanger changer;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.changer = (FragmentsChanger) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        viewModel = new ViewModelProvider(this, new ViewModelFactory(Objects.requireNonNull(getActivity()).getApplication())).get(ClassRoomListViewModel.class);

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
        viewModel.getClassrooms().observe(this, new Observer<List<ClassRoom>>() {
            @Override
            public void onChanged(List<ClassRoom> classRooms) {
                classRoomList.clear();
                classRoomList.addAll(classRooms);
                recyclerAdapter.notifyDataSetChanged();
            }
        });


        Button addClassButton = view.findViewById(R.id.addClassButton);
        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changer.openAddClassRoomFragment();
            }
        });

        Button addStudentButton = view.findViewById(R.id.addStudentButton);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changer.openAddStudentFragment();

            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.classrooms_recycle_view);
        recyclerAdapter = new ClassRoomsRecyclerAdapter(classRoomList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fragment = new SearchByFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getContext();
        assert fragmentChangeListener != null;
        fragmentChangeListener.replaceFragment(fragment);
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClickListener(ClassRoom classRoom) {
        changer.openClassRoomDetailsFragment(classRoom);
    }

    @Override
    public void onItemLongClickListener(ClassRoom classRoom) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        viewModel.deleteClassRoom(classRoom);
                        classRoomList.remove(classRoom);
                        viewModel.updateClassrooms();
                        recyclerAdapter.notifyItemRemoved(classRoomList.indexOf(classRoom));
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Delete classroom?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    @Override
    public void editButtonOnClickListener(ClassRoom classRoom) {
        changer.openClassRoomEditFragment(classRoom);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.updateClassrooms();

    }
    //    @Override
//    public void updateRooms(List<ClassRoom> all) {
//        classRoomList.clear();
//        classRoomList.addAll(all);
//        recyclerAdapter.notifyDataSetChanged();
//    }
//
//
//    @Override
//    public void openClassRoomEditFragment(ClassRoom classRoom) {
//        Fragment fragment = new EditClassRoomFragment();
//        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("pos", classRoom);
//        fragment.setArguments(bundle);
//        fragmentChangeListener.replaceFragment(fragment);
//    }
//
//    @Override
//    public void openClassRoomDetailsFragment(ClassRoom classRoom) {
//        Fragment fragment = new StudentsListFragment();
//        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("pos", classRoom);
//        fragment.setArguments(bundle);
//        fragmentChangeListener.replaceFragment(fragment);
//    }
//
//    @Override
//    public void openAddClassRoomFragment() {
//        Fragment fragment = new AddClassRoomFragment();
//        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
//        fragmentChangeListener.replaceFragment(fragment);
//    }
//
//    @Override
//    public void openAddStudentFragment() {
//
//        Fragment fragment = new AddStudentFragment();
//        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
//        fragmentChangeListener.replaceFragment(fragment);
//    }
}