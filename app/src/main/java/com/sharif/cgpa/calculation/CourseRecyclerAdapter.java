package com.sharif.cgpa.calculation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharif.cgpa.R;
import com.sharif.cgpa.model.Course;

import java.util.List;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.viewHolder> {
List<Course>allCourses;

    public CourseRecyclerAdapter(List<Course> allCourses) {
        this.allCourses = allCourses;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item_layout,parent,false);
       return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
   Course course = allCourses.get(position);
   holder.CourseGpaTextView.setText(course.getCourseGpa()+"");
   holder.CourseCreditTextView.setText(course.getCouseCredit()+"");
   holder.CourseNameTextView.setText("Course"+(position+1));
    }

    @Override
    public int getItemCount() {
       if(allCourses==null||allCourses.size()==0){
           return 0;
       }
       else {
           return allCourses.size();
       }
    }

    public class viewHolder extends RecyclerView.ViewHolder{

TextView CourseNameTextView,CourseCreditTextView,CourseGpaTextView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            CourseCreditTextView=itemView.findViewById(R.id.CourseCreditTextViewid);
            CourseGpaTextView=itemView.findViewById(R.id.CourseGpaTextViewid);
            CourseNameTextView=itemView.findViewById(R.id.CourseNameTextViewid);
        }
    }
}


