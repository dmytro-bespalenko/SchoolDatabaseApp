package com.example.schooldatabaseapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class EntityClassRoom implements Parcelable {

    @PrimaryKey
    private Integer id;
    @ColumnInfo(name = "class_name")
    private String className;
    @ColumnInfo(name = "class_number")
    public Integer classNumber;
    @ColumnInfo(name = "students_count")
    private Integer studentsCount;
    @ColumnInfo(name = "floor")
    private Integer floor;
    @Ignore
    public EntityClassRoom(String className, int classNumber, int studentsCount, int floor) {
        this.id = null;
        this.className = className;
        this.classNumber = classNumber;
        this.studentsCount = studentsCount;
        this.floor = floor;
    }

    public EntityClassRoom(Integer id, String className, int classNumber, int studentsCount, int floor) {
        this.id = id;
        this.className = className;
        this.classNumber = classNumber;
        this.studentsCount = studentsCount;
        this.floor = floor;
    }

    protected EntityClassRoom(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        className = in.readString();
        classNumber = in.readInt();
        studentsCount = in.readInt();
        floor = in.readInt();
    }

    public static final Creator<EntityClassRoom> CREATOR = new Creator<EntityClassRoom>() {
        @Override
        public EntityClassRoom createFromParcel(Parcel in) {
            return new EntityClassRoom(in);
        }

        @Override
        public EntityClassRoom[] newArray(int size) {
            return new EntityClassRoom[size];
        }
    };

    public int getClassId() {
        return id;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(className);
        dest.writeInt(classNumber);
        dest.writeInt(studentsCount);
        dest.writeInt(floor);
    }


}
