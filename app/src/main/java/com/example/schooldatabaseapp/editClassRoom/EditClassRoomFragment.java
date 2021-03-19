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
import android.widget.Button;
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
//    private ClassRoomsRecyclerAdapter recyclerAdapter;
//    private List<ClassRoom> classRoomList = new ArrayList<>();

    private EditText editClassName;
    private EditText editClassNumber;
    private EditText editFloor;
    private EditText idClass;
    private ClassRoom classRoom;

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

        editClassName = view.findViewById(R.id.editClassName);
        editClassNumber = view.findViewById(R.id.editClassNumber);
        editFloor = view.findViewById(R.id.editFloor);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            classRoom = bundle.getParcelable("pos");

            editClassName.setText(classRoom.getClassName());
            editClassNumber.setText(String.valueOf(classRoom.getClassNumber()));
            editFloor.setText(String.valueOf(classRoom.getFloor()));
        }

        Button button = view.findViewById(R.id.saveEditButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEditFields();
            }
        });


    }

    public void validateEditFields() {
        if (editClassName.getText().toString().length() == 0) {
            editClassName.setError("Class name is required!");

        } else if (editClassNumber.getText().toString().length() == 0) {
            editClassNumber.setError("Class number is required!");
        } else if (editFloor.getText().toString().length() == 0) {
            editFloor.setError("Floor is required!");
        } else {
            String className = String.valueOf(editClassName.getText());
            int classNumber = Integer.parseInt(editClassNumber.getText().toString());
            int floor = Integer.parseInt(editFloor.getText().toString());
            presenter.editClassRoom(new ClassRoom(classRoom.getId(), className, classNumber, 0, floor));
            assert getFragmentManager() != null;
            getFragmentManager().popBackStack();
        }
    }


}