package com.example.schooldatabaseapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.Callable;

public class ClassRoom implements Parcelable, Callable<ClassRoom> {

    private Integer id;
    private String className;
    public int classNumber;
    private int studentsCount;
    private int floor;

    public ClassRoom(String className, int classNumber, int studentsCount, int floor) {
        this.id = null;
        this.className = className;
        this.classNumber = classNumber;
        this.studentsCount = studentsCount;
        this.floor = floor;
    }

    public ClassRoom(int id, String className, int classNumber, int studentsCount, int floor) {
        this.id = id;
        this.className = className;
        this.classNumber = classNumber;
        this.studentsCount = studentsCount;
        this.floor = floor;
    }

    protected ClassRoom(Parcel in) {
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

    public static final Creator<ClassRoom> CREATOR = new Creator<ClassRoom>() {
        @Override
        public ClassRoom createFromParcel(Parcel in) {
            return new ClassRoom(in);
        }

        @Override
        public ClassRoom[] newArray(int size) {
            return new ClassRoom[size];
        }
    };

    public int getClassId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getFloor() {
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

    @Override
    public ClassRoom call() throws Exception {
        return null;
    }
}
