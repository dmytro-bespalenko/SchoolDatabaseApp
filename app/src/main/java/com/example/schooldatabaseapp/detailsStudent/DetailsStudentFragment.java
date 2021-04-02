package com.example.schooldatabaseapp.detailsStudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.base.FragmentChangeListener;
import com.example.schooldatabaseapp.editStudent.EditStudentFragment;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public class DetailsStudentFragment extends Fragment implements DetailsStudentContract.View {


    private TextView idView;
    private TextView firstNameView;
    private TextView lastNameView;
    private TextView genderView;
    private TextView ageView;
    private TextView studentClassname;
    private Student student;
    private Button editStudent;
    private Button deleteStudent;
    private List<ClassRoom> classRoomList;

    private DetailsStudentContract.Presenter presenter;
    private String className;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_details_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new DetailsStudentPresenter(this);
        classRoomList = presenter.getAllClassRooms();

        idView = view.findViewById(R.id.student_id);
        firstNameView = view.findViewById(R.id.student_first_name);
        lastNameView = view.findViewById(R.id.student_last_name);
        genderView = view.findViewById(R.id.student_gender);
        ageView = view.findViewById(R.id.student_age);
        studentClassname = view.findViewById(R.id.student_classname);
        editStudent = view.findViewById(R.id.edit_student);
        deleteStudent = view.findViewById(R.id.delete_student);

        Bundle bundle = this.getArguments();


        if (bundle != null) {
            student = bundle.getParcelable("pos");
            idView.setText(String.valueOf(student.getId()));
            firstNameView.setText(student.getFirstName());
            lastNameView.setText(student.getLastName());
            genderView.setText(student.getGender());
            ageView.setText(String.valueOf(student.getAge()));
            for (int i = 0; i < classRoomList.size(); i++) {
                if (student.getClassId() == classRoomList.get(i).getClassId()) {
                    className = classRoomList.get(i).getClassName();
                }
            }
            studentClassname.setText(className);

        }

        removeStudentButton();

        editStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openEditStudentFragment();
            }
        });


    }


    public void removeStudentButton() {
        deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked

                                presenter.deleteStudent(student);
                                assert getFragmentManager() != null;
                                getFragmentManager().popBackStack();

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Delete student " + student.getLastName() + " " + student.getLastName() + "?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();


            }
        });
    }

    @Override
    public void openEditStudentFragment() {
        Fragment fragment = new EditStudentFragment();
        FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pos", student);
        fragment.setArguments(bundle);
        fragmentChangeListener.replaceFragment(fragment);

    }


    @Override
    public void deleteCurrentStudent(List<Student> students, int delete) {
        students.remove(delete);
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}