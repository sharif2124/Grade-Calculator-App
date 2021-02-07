package com.sharif.cgpa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String courseName;
    public double courseGpa;
    public double couseCredit;
    public int semesterId;

    public Course( double courseGpa, double couseCredit, int semesterId) {

        this.courseGpa = courseGpa;
        this.couseCredit = couseCredit;
        this.semesterId = semesterId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseGpa() {
        return courseGpa;
    }

    public void setCourseGpa(double courseGpa) {
        this.courseGpa = courseGpa;
    }

    public double getCouseCredit() {
        return couseCredit;
    }

    public void setCouseCredit(double couseCredit) {
        this.couseCredit = couseCredit;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }
}
