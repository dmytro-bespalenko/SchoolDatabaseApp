package com.example.schooldatabaseapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class SearchByStudentsRecyclerAdapter extends RecyclerView.Adapter<SearchByStudentsRecyclerAdapter.ViewHolder> implements Filterable {


    private List<Student> studentList;
    private List<Student> filteredStudentList;
    public CallBackAdapterPosition adapterPosition;

    public interface CallBackAdapterPosition {
        void adapterPosition(Student position);
    }


    public SearchByStudentsRecyclerAdapter(List<Student> studentList, SearchByStudentsRecyclerAdapter.CallBackAdapterPosition callBackAdapterPosition) {
        this.studentList = studentList;
        adapterPosition = callBackAdapterPosition;
        filteredStudentList = new ArrayList<>(studentList);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_card, parent, false), filteredStudentList, adapterPosition);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);

        holder.searchResult.setText(student.getFirstName() + " " + student.getLastName());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    @Override
    public Filter getFilter() {
        return studentFilter;

    }

    private final Filter studentFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Student> resultList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                resultList.addAll(filteredStudentList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Student student : filteredStudentList) {
                    if (student.getFirstName().toLowerCase().contains(filterPattern)) {
                        resultList.add(student);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = resultList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            studentList.clear();
            studentList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    // FIXME: 31.03.21
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView searchResult;

        public ViewHolder(@NonNull View itemView, List<Student> filteredStudentList, CallBackAdapterPosition adapterPosition) {
            super(itemView);
            searchResult = itemView.findViewById(R.id.search_result_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterPosition.adapterPosition(filteredStudentList.get(getAdapterPosition()));
                }
            });
        }
    }
}
