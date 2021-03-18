package com.example.schooldatabaseapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldatabaseapp.R;
import com.example.schooldatabaseapp.model.ClassRoom;
import com.example.schooldatabaseapp.classRoom.ClassRoomContract;
import com.example.schooldatabaseapp.model.DatabaseClassRoomRepository;
import com.example.schooldatabaseapp.model.DatabaseHelper;

import java.util.List;

public class ClassRoomsRecyclerAdapter extends RecyclerView.Adapter<ClassRoomsRecyclerAdapter.ViewHolder> {


    private List<ClassRoom> classRooms;
    private ClassRoomContract.Presenter presenter;


    public ClassRoomsRecyclerAdapter(List<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }

    public void registerListener(ClassRoomContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.classroom_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassRoom classRoom = classRooms.get(position);
        CardView cv = holder.cardView;

        TextView idView = cv.findViewById(R.id.classId);
        idView.setText(String.valueOf(classRoom.getId()));

        TextView nameView = cv.findViewById(R.id.className);
        nameView.setText("Name: " + classRoom.getClassName());

        TextView classNumberView = cv.findViewById(R.id.classNumber);
        classNumberView.setText("Number: " + classRoom.getClassNumber());

        TextView studentsCountView = cv.findViewById(R.id.studentsCount);
        studentsCountView.setText("Count: " + classRoom.getStudentsCount());

        TextView floorView = cv.findViewById(R.id.floor);
        floorView.setText("Floor: " + classRoom.getFloor());

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
        final TextView studentsCount;
        final TextView floor;

        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
            classId = itemView.findViewById(R.id.classId);
            className = itemView.findViewById(R.id.className);
            classNumber = itemView.findViewById(R.id.classNumber);
            studentsCount = itemView.findViewById(R.id.studentsCount);
            floor = itemView.findViewById(R.id.floor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    presenter.onButtonWasClicked(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (!classRooms.isEmpty()) {
                        presenter.onItemWasLongClick(classRooms, getAdapterPosition());
                    }
                    return true;
                }
            });

        }
    }
}
