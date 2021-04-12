package com.example.schooldatabaseapp.classRoom;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.schooldatabaseapp.adapters.ClassRoomsRecyclerAdapter;
import com.example.schooldatabaseapp.addClass.AddClassRoomFragment;
import com.example.schooldatabaseapp.addStudents.AddStudentFragment;
import com.example.schooldatabaseapp.base.FragmentChangeListener;
import com.example.schooldatabaseapp.editClassRoom.EditClassRoomFragment;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.room.repository.RoomClassRoomRepository;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.students.StudentsListFragment;

import java.util.ArrayList;
import java.util.List;


public class ClassRoomsListFragment extends Fragment implements ClassRoomListContract.View, ClassRoomsRecyclerAdapter.CallBackAdapterPosition {


    private static final String TAG = "My_Tag";
    private ClassRoomsRecyclerAdapter recyclerAdapter;
    private List<ClassRoom> classRoomList = new ArrayList<>();
    private ClassRoomListContract.Presenter presenter;
    private List<Student> studentList;

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
        presenter = new ClassRoomListPresenter(this, RoomClassRoomRepository.getInstance());
        studentList = presenter.getAllStudents();

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
        recyclerAdapter = new ClassRoomsRecyclerAdapter(classRoomList, this);
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
    public void openClassRoomEditFragment(ClassRoom classRoom) {
        Fragment fragment = new EditClassRoomFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pos", classRoom);
        fragment.setArguments(bundle);
        fragmentChangeListener.replaceFragment(fragment);
    }

    @Override
    public void openClassRoomDetailsFragment(ClassRoom classRoom) {
        Fragment fragment = new StudentsListFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pos", classRoom);
        fragment.setArguments(bundle);
        fragmentChangeListener.replaceFragment(fragment);
    }

    @Override
    public void openAddClassRoomFragment() {
        Fragment fragment = new AddClassRoomFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
        fragmentChangeListener.replaceFragment(fragment);
    }

    @Override
    public void openAddStudentFragment() {

        Fragment fragment = new AddStudentFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();

        fragmentChangeListener.replaceFragment(fragment);
    }


    @Override
    public void editButtonOnClickListener(ClassRoom classRoom) {
        presenter.openEditFragment(classRoom);
    }

    @Override
    public void onItemClickListener(ClassRoom classRoom) {
        presenter.onItemClickListener(classRoom);
    }

    @Override
    public void onItemLongClickListener(ClassRoom classRoom) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        for (int i = 0; i < studentList.size(); i++) {
                            if (classRoom.getClassId() == studentList.get(i).getClassId()) {
                                presenter.deleteStudent(studentList.get(i));
                            }
                        }
                        presenter.deleteClassRoom(classRoom);
                        classRoomList.remove(classRoom);
                        presenter.updateClassRooms();
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
    public void onResume() {
        super.onResume();
        presenter.updateClassRooms();


    }
}