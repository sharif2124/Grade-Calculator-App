package com.sharif.cgpa;

import android.app.Application;
import android.os.AsyncTask;

import androidx.room.Delete;

import com.sharif.cgpa.model.Course;
import com.sharif.cgpa.model.Semister;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GradeRepository {
    private CourseDao courseDao;
    private SemisterDao semisterDao;
    List<Semister> mySemisterlist = new ArrayList<>();
    List<Course> allCourses = new ArrayList<>();

    private GetCourseListTask getCourseListTask;

    public GradeRepository(Application application) {
        GradeDatabase db = GradeDatabase.getDatabase(application);
        courseDao = db.courseDao();
        semisterDao = db.semisterDao();

    }

    public void InsertSemister(Semister semister) {
        new InsertTask(semisterDao).execute(semister);

    }

    public void InsertCourseList(List<Course> myCourses) {
        new coursesListTask(courseDao).execute(myCourses);
    }
public List<Course> GetCourseById(int semisterId){
    try {
        allCourses =  new GetCourseListTask(courseDao).execute(semisterId).get();
    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return allCourses;

}

    public List<Semister> GetAllSemister() {
        try {
            mySemisterlist = new GetAllSemisterTask(semisterDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mySemisterlist;


    }
    public  void Delatecourse(Course course){
        new delateCourseTask(courseDao).execute(course);

    }


    //background Task
    private static class InsertTask extends AsyncTask<Semister, Void, Void> {
        private SemisterDao dao;

        InsertTask(SemisterDao semisterDao) {
            dao = semisterDao;
        }

        @Override
        protected Void doInBackground(Semister... semisters) {
            dao.InsertSemister(semisters[0]);
            return null;
        }
    }

    private static class GetAllSemisterTask extends AsyncTask<Void, Void, List<Semister>> {
        SemisterDao dao;

        GetAllSemisterTask(SemisterDao semisterDao) {
            dao = semisterDao;
        }

        @Override
        protected List<Semister> doInBackground(Void... voids) {

            return dao.GetAllSemister();
        }
    }

    private static class coursesListTask extends AsyncTask<List<Course>, Void, Void> {
        CourseDao dao;

        coursesListTask(CourseDao courseDao) {
            dao = courseDao;
        }

        @Override
        protected Void doInBackground(List<Course>... lists) {
            dao.InsertCourselist(lists[0]);
            return null;
        }
    }

    private static class GetCourseListTask extends AsyncTask<Integer, Void, List<Course>> {
        CourseDao dao;

        GetCourseListTask(CourseDao courseDao) {
            dao = courseDao;
        }

        @Override
        protected List<Course> doInBackground(Integer... integers) {
            return dao.GetCourseBysemesterId(integers[0]);

        }
    }

    private static class courseListtask extends AsyncTask<List<Course>, Void, Void> {
        CourseDao dao;

        courseListtask(CourseDao courseDao) {
            dao = courseDao;
        }

        @Override
        protected Void doInBackground(List<Course>... lists) {
            dao.InsertCourselist(lists[0]);
            return null;
        }
    }
    private static class delateCourseTask extends AsyncTask<Course,Void,Void>{
    CourseDao dao;
        delateCourseTask(CourseDao courseDao){

            dao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            dao.DelateCourse(courses[0]);
            return null;
        }
    }
}




