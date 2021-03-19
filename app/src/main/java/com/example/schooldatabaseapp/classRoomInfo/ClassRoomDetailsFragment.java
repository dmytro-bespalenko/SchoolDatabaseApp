package com.example.schooldatabaseapp.classRoomInfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.model.ClassRoom;

public class ClassRoomDetailsFragment extends Fragment implements ClassRoomDetailsContract.View {


    private ClassRoomDetailsContract.Presenter presenter;
    private TextView className;
    private TextView classNumber;
    private TextView studentsCount;
    private TextView floor;
    private ClassRoom classRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_room_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ClassRoomDetailsPresenter(this, view.getContext());
        className = view.findViewById(R.id.classNameInfo);
        classNumber = view.findViewById(R.id.classNumberInfo);
        studentsCount = view.findViewById(R.id.studentsCountInfo);
        floor = view.findViewById(R.id.floorInfo);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            classRoom = bundle.getParcelable("pos");

            className.setText("Name: " + classRoom.getClassName());
            classNumber.setText("№ " + classRoom.getClassNumber());
            studentsCount.setText("Count " + classRoom.getStudentsCount());
            floor.setText("Floor " + classRoom.getFloor());
        }


    }

}