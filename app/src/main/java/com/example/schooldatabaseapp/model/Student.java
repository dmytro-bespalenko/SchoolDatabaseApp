package com.example.schooldatabaseapp.model;

public class Student {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer classId;
    private String gender;
    private int age;


    public Student(Integer id, String firstName, String lastName, int classId, String gender, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
        this.gender = gender;
        this.age = age;
    }

    public Student(String firstName, String lastName, int classId, String gender, int age) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
        this.gender = gender;
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


}
