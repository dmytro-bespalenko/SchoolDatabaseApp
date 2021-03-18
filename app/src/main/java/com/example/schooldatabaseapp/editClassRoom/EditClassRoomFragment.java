package com.example.schooldatabaseapp.editClassRoom;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.addClass.AddClassRoomContract;
import com.example.schooldatabaseapp.addClass.AddClassRoomFragment;
import com.example.schooldatabaseapp.classRoom.ClassRoomContract;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.view.ClassRoomsRecyclerAdapter;
import com.example.schooldatabaseapp.view.FragmentChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditClassRoomFragment extends Fragment implements EditClassRoomContract.View {


    private static final String TAG = "My_Tag";
    private EditClassRoomContract.Presenter presenter;
    private ClassRoomsRecyclerAdapter recyclerAdapter;
    private List<ClassRoom> classRoomList = new ArrayList<>();

    private EditText editClassName;
    private EditText editClassNumber;
    private EditText editFloor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_class_room, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new EditClassRoomPresenter(this, view.getContext());

        recyclerAdapter = new ClassRoomsRecyclerAdapter(classRoomList);
        recyclerAdapter.registerEditListener(presenter);

        editClassName = view.findViewById(R.id.editClassName);
        editClassNumber = view.findViewById(R.id.editClassNumber);
        editFloor = view.findViewById(R.id.editFloor);

        ClassRoom classRoom;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            classRoom = bundle.getParcelable("pos");
            editClassName.setText(classRoom.getClassName());
            editClassNumber.setText(String.valueOf(classRoom.getClassNumber()));
            editFloor.setText(String.valueOf(classRoom.getFloor()));
        }




    }

//    public void validateEditFields() {
//        if (editClassName.getText().toString().length() == 0) {
//            editClassName.setError("Class name is required!");
//        }
//        if (editClassNumber.getText().toString().length() == 0) {
//            editClassNumber.setError("Class number is required!");
//        }
//        if (editFloor.getText().toString().length() == 0) {
//            editFloor.setError("Floor is required!");
//        } else {
//            String className = String.valueOf(editClassName.getText());
//            int classNumber = Integer.parseInt(editClassNumber.getText().toString());
//            int floor = Integer.parseInt(editFloor.getText().toString());
//            presenter.addNewClassRoom (className, classNumber, floor);
//            assert getFragmentManager() != null;
//            getFragmentManager().popBackStack();
//        }
//    }

    @Override
    public void updateRooms(List<ClassRoom> all) {
        classRoomList.clear();
        classRoomList.addAll(all);
        recyclerAdapter.notifyDataSetChanged();
    }
}