package com.example.schooldatabaseapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.students.StudentsContract;

import java.util.List;

public class StudentsRecyclerAdapter extends RecyclerView.Adapter<StudentsRecyclerAdapter.ViewHolder> {

    private List<Student> studentsList;
    private StudentsContract.Presenter studentsPresenter;


    public StudentsRecyclerAdapter(List<Student> studentsList) {
        this.studentsList = studentsList;
    }


    public void registerStudentsListener(StudentsContract.Presenter presenter) {
        this.studentsPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.student_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsRecyclerAdapter.ViewHolder holder, int position) {
        Student student = studentsList.get(position);

        holder.studentName.setText(student.getFirstName() + " " + student.getLastName() + " classId " + student.getClassId());
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView studentName;

        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.students_full_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    studentsPresenter.onItemClickListener(studentsList.get(getAdapterPosition()));
                }
            });

        }


    }
}
