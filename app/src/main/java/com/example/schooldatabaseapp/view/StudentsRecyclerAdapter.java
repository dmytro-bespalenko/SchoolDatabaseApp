package com.example.schooldatabaseapp.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.classRoom.ClassRoomContract;
import com.example.schooldatabaseapp.model.Student;

import java.util.List;

public class StudentsRecyclerAdapter extends RecyclerView.Adapter<StudentsRecyclerAdapter.ViewHolder> {

    private List<Student> studentsList;
    private ClassRoomContract.Presenter classRoomPresenter;


    public StudentsRecyclerAdapter(List<Student> studentsList) {
        this.studentsList = studentsList;
    }


    public void registerStudentsListener(ClassRoomContract.Presenter presenter) {
        this.classRoomPresenter = presenter;
    }

    @NonNull
    @Override
    public StudentsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentsRecyclerAdapter.ViewHolder((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.student_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsRecyclerAdapter.ViewHolder holder, int position) {
        Student student = studentsList.get(position);
        CardView cv = holder.cardView;

        TextView studentName = cv.findViewById(R.id.students_full_name);




    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;


        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }
}
