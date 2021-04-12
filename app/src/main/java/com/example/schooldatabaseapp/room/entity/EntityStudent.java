package com.example.schooldatabaseapp.room.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import static androidx.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey(entity = EntityClassRoom.class, parentColumns = "id", childColumns = "classname_id", onDelete = CASCADE))
public class EntityStudent implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "first_name")
    private String firstName;
    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "classname_id", index = true)
    private Integer classId;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "age")
    private Integer age;

    @Ignore
    public EntityStudent(String firstName, String lastName, int classId, String gender, int age) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
        this.gender = gender;
        this.age = age;
    }

    public EntityStudent(int id, String firstName, String lastName, int classId, String gender, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
        this.gender = gender;
        this.age = age;
    }


    protected EntityStudent(Parcel in) {
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

    public static final Creator<EntityStudent> CREATOR = new Creator<EntityStudent>() {
        @Override
        public EntityStudent createFromParcel(Parcel in) {
            return new EntityStudent(in);
        }

        @Override
        public EntityStudent[] newArray(int size) {
            return new EntityStudent[size];
        }
    };

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }



    public Integer getClassId() {
        return classId;
    }


    public String getGender() {
        return gender;
    }


    public Integer getAge() {
        return age;
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
