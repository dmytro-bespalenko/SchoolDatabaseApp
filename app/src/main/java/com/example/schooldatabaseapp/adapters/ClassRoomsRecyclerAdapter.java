package com.example.schooldatabaseapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.classRoom.ClassRoomListContract;

import java.util.List;

public class ClassRoomsRecyclerAdapter extends RecyclerView.Adapter<ClassRoomsRecyclerAdapter.ViewHolder> {


    private List<ClassRoom> classRooms;
    private ClassRoomListContract.Presenter classRoomPresenter;


    public ClassRoomsRecyclerAdapter(List<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }


    public void registerClassRoomsListener(ClassRoomListContract.Presenter presenter) {
        this.classRoomPresenter = presenter;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.classroom_card, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassRoom classRoom = classRooms.get(position);
        CardView cv = holder.cardView;

        TextView idView = cv.findViewById(R.id.classId);
        idView.setText(String.valueOf(classRoom.getClassId()));

        TextView nameView = cv.findViewById(R.id.className);
        nameView.setText("Classname: " + classRoom.getClassName());

        TextView classNumberView = cv.findViewById(R.id.classNumber);
        classNumberView.setText("№" + classRoom.getClassNumber());


    }

    @Override
    public int getItemCount() {
        return classRooms.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        final TextView classId;
        final TextView className;
        final TextView classNumber;
        final ImageButton editButton;


        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
            classId = itemView.findViewById(R.id.classId);
            className = itemView.findViewById(R.id.className);
            classNumber = itemView.findViewById(R.id.classNumber);
            editButton = itemView.findViewById(R.id.editButton);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    classRoomPresenter.openEditFragment(classRooms.get(getAdapterPosition()));

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    classRoomPresenter.onItemClickListener(classRooms.get(getAdapterPosition()));

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    classRoomPresenter.onItemWasLongClick(classRooms, getAdapterPosition());

                    return true;
                }
            });

        }
    }
}
