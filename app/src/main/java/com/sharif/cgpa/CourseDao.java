package com.sharif.cgpa;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sharif.cgpa.model.Course;

import java.util.List;

@Dao
public interface CourseDao {
@Insert
    void  InsertCourse(Course course);
@Delete
    void DelateCourse(Course course);
@Update
    void UpdateCourse(Course course);
@Insert
    void InsertCourselist(List<Course> Couses);

@Query("select * from Course where semesterId Like :semesterId")
    List<Course> GetCourseBysemesterId (int semesterId);
@Query("Delete from Course")
   void DelateAllCourses();
}
