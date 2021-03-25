package com.example.schooldatabaseapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer classId;
    private String gender;
    private int age;


    public Student(String firstName, String lastName, int classId, String gender, int age) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
        this.gender = gender;
        this.age = age;
    }

    public Student(int id, String firstName, String lastName, int classId, String gender, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
        this.gender = gender;
        this.age = age;
    }


    protected Student(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        firstName = in.readString();
        lastName = in.readString();
        if (in.readByte() == 0) {
            classId = null;
        } else {
            classId = in.readInt();
        }
        gender = in.readString();
        age = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        dest.writeString(firstName);
        dest.writeString(lastName);
        if (classId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(classId);
        }
        dest.writeString(gender);
        dest.writeInt(age);
    }
}
