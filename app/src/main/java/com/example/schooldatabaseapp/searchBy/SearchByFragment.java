package com.example.schooldatabaseapp.searchBy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.adapters.SearchByClassRoomsRecyclerAdapter;
import com.example.schooldatabaseapp.adapters.SearchByStudentsRecyclerAdapter;
import com.example.schooldatabaseapp.base.FragmentChangeListener;
import com.example.schooldatabaseapp.detailsStudent.DetailsStudentFragment;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.students.StudentsListFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class SearchByFragment extends Fragment implements SearchByContract.View {

    private static final String TAG = "My_Tag";
    private SearchByStudentsRecyclerAdapter recyclerStudentsAdapter;
    private SearchByClassRoomsRecyclerAdapter recyclerClassroomsAdapter;

    private List<Student> studentList = new ArrayList<>();
    private List<ClassRoom> classRoomList = new ArrayList<>();

    private SearchByContract.Presenter presenter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private Spinner spinner;
    private Executor executor = Executors.newSingleThreadExecutor();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_search_by, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.edit_search_view_query);

        presenter = new SearchByPresenter(this, requireContext());
        recyclerView = view.findViewById(R.id.search_result_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        spinner = view.findViewById(R.id.spinner_search_by);

        setSearchBySpinner();
    }


    public void setSearchBySpinner() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    recyclerStudentsAdapter = new SearchByStudentsRecyclerAdapter(studentList);
                    recyclerStudentsAdapter.registerSearchByListener(presenter);
                    recyclerView.setAdapter(recyclerStudentsAdapter);
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {


                            recyclerStudentsAdapter.getFilter().filter(newText);

                            return false;
                        }
                    });
                } else {
                    recyclerClassroomsAdapter = new SearchByClassRoomsRecyclerAdapter(classRoomList);
                    recyclerClassroomsAdapter.registerSearchByListener(presenter);
                    recyclerView.setAdapter(recyclerClassroomsAdapter);
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {

                            recyclerClassroomsAdapter.getFilter().filter(newText);

                            return false;
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

    }

    @Override
    public void updateStudents(List<Student> all) {
        studentList.clear();


        executor.execute(new Runnable() {
            @Override
            public void run() {
                studentList.addAll(all);

                Collections.sort(studentList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o1.getFirstName().compareTo(o2.getFirstName());
                    }
                });
                Log.d(TAG, "run: " + Thread.currentThread().getName());
            }
        });
        recyclerStudentsAdapter = new SearchByStudentsRecyclerAdapter(studentList);
        recyclerStudentsAdapter.registerSearchByListener(presenter);
        recyclerView.setAdapter(recyclerStudentsAdapter);
        recyclerStudentsAdapter.notifyDataSetChanged();

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
    public void updateClassRooms(List<ClassRoom> allClassRoom) {
        classRoomList.clear();
        classRoomList.addAll(allClassRoom);
        Collections.sort(classRoomList, new Comparator<ClassRoom>() {
            @Override
            public int compare(ClassRoom o1, ClassRoom o2) {
                return o1.getClassName().compareTo(o2.getClassName());
            }
        });
        recyclerClassroomsAdapter = new SearchByClassRoomsRecyclerAdapter(classRoomList);
        recyclerStudentsAdapter.registerSearchByListener(presenter);
        recyclerView.setAdapter(recyclerClassroomsAdapter);
        recyclerClassroomsAdapter.notifyDataSetChanged();
    }

    @Override
    public void openStudentsDetailsFragment(Student student) {
        Fragment fragment = new DetailsStudentFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pos", student);
        fragment.setArguments(bundle);
        fragmentChangeListener.replaceFragment(fragment);

    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.updateStudents();
        presenter.updateClassRooms();
    }
}