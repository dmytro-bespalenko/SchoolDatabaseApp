package com.example.schooldatabaseapp.model;

public class ClassRoom {

    private Integer id;
    private String className;
    private int classNumber;
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

    public int getId() {
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
}
