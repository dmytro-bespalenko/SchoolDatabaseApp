package com.example.schooldatabaseapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.model.Student;
import com.example.schooldatabaseapp.students.StudentsListContract;

import java.util.List;

public class StudentsRecyclerAdapter extends RecyclerView.Adapter<StudentsRecyclerAdapter.ViewHolder> {

    private List<Student> studentsList;

    CallBackAdapterPosition callBackAdapterPosition;

    public interface CallBackAdapterPosition {
        void onItemClickListener(Student student);

    }

    public StudentsRecyclerAdapter(List<Student> studentsList, CallBackAdapterPosition callBackAdapterPosition) {
        this.studentsList = studentsList;
        this.callBackAdapterPosition = callBackAdapterPosition;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.student_card, parent, false), studentsList, callBackAdapterPosition);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentsRecyclerAdapter.ViewHolder holder, int position) {
        Student student = studentsList.get(position);

        holder.studentName.setText(student.getFirstName() + " " + student.getLastName());
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView studentName;

        public ViewHolder(@NonNull CardView itemView, List<Student> studentsList, CallBackAdapterPosition callBackAdapterPosition) {
            super(itemView);
            studentName = itemView.findViewById(R.id.students_full_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBackAdapterPosition.onItemClickListener(studentsList.get(getAdapterPosition()));
                }
            });


        }


    }
}
