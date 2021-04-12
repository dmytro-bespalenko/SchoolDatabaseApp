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
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.room.entity.EntityClassRoom;

import java.util.ArrayList;
import java.util.List;

public class SearchByClassRoomsRecyclerAdapter extends RecyclerView.Adapter<SearchByClassRoomsRecyclerAdapter.ViewHolder> implements Filterable {


    private final List<ClassRoom> classRoomList;
    private final List<ClassRoom> filteredClassRoomList;
    private CallBackAdapterPosition callBackAdapterPosition;



    public interface CallBackAdapterPosition {
        void adapterPosition(ClassRoom position);
    }


    public SearchByClassRoomsRecyclerAdapter(List<ClassRoom> classRoomList, CallBackAdapterPosition callBackAdapterPosition) {
        this.classRoomList = classRoomList;
        this.filteredClassRoomList = new ArrayList<>(classRoomList);
        this.callBackAdapterPosition = callBackAdapterPosition;
    }


    @NonNull
    @Override
    public SearchByClassRoomsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_card, parent, false), callBackAdapterPosition, filteredClassRoomList);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SearchByClassRoomsRecyclerAdapter.ViewHolder holder, int position) {
        ClassRoom classRoom = classRoomList.get(position);

        holder.searchResult.setText(classRoom.getClassName());
    }

    @Override
    public int getItemCount() {
        return classRoomList.size();
    }

    @Override
    public Filter getFilter() {
        return classRoomFilter;

    }

    private final Filter classRoomFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ClassRoom> resultList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                resultList.addAll(filteredClassRoomList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ClassRoom classRoom : filteredClassRoomList) {
                    if (classRoom.getClassName().toLowerCase().contains(filterPattern)) {
                        resultList.add(classRoom);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = resultList;

            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            classRoomList.clear();
            classRoomList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView searchResult;

        public ViewHolder(@NonNull View itemView, CallBackAdapterPosition callBackAdapterPosition, List<ClassRoom> filteredClassRoomList) {
            super(itemView);
            searchResult = itemView.findViewById(R.id.search_result_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBackAdapterPosition.adapterPosition(filteredClassRoomList.get(getAdapterPosition()));
                }
            });
        }
    }
}
