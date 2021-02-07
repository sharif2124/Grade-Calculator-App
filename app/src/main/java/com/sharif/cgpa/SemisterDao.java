package com.sharif.cgpa;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sharif.cgpa.model.Course;
import com.sharif.cgpa.model.Semister;

import java.util.List;


@Dao

public interface SemisterDao {
    @Insert
    void  InsertSemister(Semister semister);
    @Delete
    void DelateSemister(Semister semister);
    @Update
    void UpdateSemister(Semister semister);
    @Query("select * from Semister Order by id ASC")
    List<Semister> GetAllSemister();

}
