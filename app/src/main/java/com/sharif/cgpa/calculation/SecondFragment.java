package com.sharif.cgpa.calculation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sharif.cgpa.DataController;
import com.sharif.cgpa.GradeRepository;
import com.sharif.cgpa.R;
import com.sharif.cgpa.model.Course;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
View rootview;
DataController controller;
EditText creditText,gpaText;
Button addbutton;
TextView cgpaTextView;
double TotalCredit=0;
double ProductofGPAandCredit=0;
RecyclerView recyclerView;
CourseRecyclerAdapter adapter;
List<Course> myCourses=new ArrayList<>();
FloatingActionButton fab;
GradeRepository repository;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.fragment_second, container, false);
        controller = DataController.getInstance();
        repository = new GradeRepository(getActivity().getApplication());
        myCourses = repository.GetCourseById(controller.getCurrentSemister().getId());
        cgpaTextView = rootview.findViewById(R.id.textView3);
        if(myCourses.size()>0){
            CalculateCGPAList(myCourses);
        }
        creditText = rootview.findViewById(R.id.editTextTextPersonName2);
        gpaText = rootview.findViewById(R.id.editTextTextPersonName);
        addbutton = rootview.findViewById(R.id.button);
        recyclerView = rootview.findViewById(R.id.courseRecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CourseRecyclerAdapter(myCourses);
        recyclerView.setAdapter(adapter);
        fab=rootview.findViewById(R.id.fab_coursefragment);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
             Delate(viewHolder.getAdapterPosition());
                new AlertDialog.Builder(getActivity())
                        .setMessage("Do you want Delate ")
                        .setTitle("Warning")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Save Courselist
                                if(myCourses==null||myCourses.size()==0){
                                    Toast.makeText(getActivity(), "Add a course First", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                   repository.InsertCourseList(myCourses);

                                    Toast.makeText(getActivity(), "Courses Delate!!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();



            }
        }).attachToRecyclerView(recyclerView);


        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(creditText.getText().toString().equals("")||gpaText.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please Insert All Fields", Toast.LENGTH_SHORT).show();
                }else{
                    CalculateCGPA(gpaText.getText().toString(),creditText.getText().toString());
                }

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setMessage("Do you want save ")
                        .setTitle("Warning")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            // Save Courselist
                                if(myCourses==null||myCourses.size()==0){
                                    Toast.makeText(getActivity(), "Add a course First", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    repository.InsertCourseList(myCourses);
                                    Toast.makeText(getActivity(), "Courses Saved!!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        Toast.makeText(getActivity(), controller.getCurrentSemister().getSemisterName(), Toast.LENGTH_SHORT).show();
        return rootview;
    }

    private void CalculateCGPAList(List<Course> myCourses) {
        for(int i =0; i<myCourses.size(); i++){
            Course temp = myCourses.get(i);
            TotalCredit+=temp.getCouseCredit();
            ProductofGPAandCredit+=(temp.getCouseCredit()*temp.getCourseGpa());


        }
        double cgpa = (ProductofGPAandCredit /TotalCredit);
        cgpaTextView.setText(String.format("CGPA : %.2f",cgpa));


    }

    private  void CalculateCGPA(String gpa,String Credit){

double gpavalue = Double.parseDouble(gpa);
double creditvalue = Double.parseDouble(Credit);
ProductofGPAandCredit+=(gpavalue*creditvalue);
TotalCredit+=creditvalue;
double cgpa = (ProductofGPAandCredit /TotalCredit);
cgpaTextView.setText(String.format("CGPA : %.2f",cgpa));

Course course = new Course(gpavalue,creditvalue,controller.getCurrentSemister().getId());
myCourses.add(course);
adapter.notifyDataSetChanged();

}
private void Delate(int position){
Course course = myCourses.get(position);
repository.Delatecourse(course);
myCourses.remove(course);
adapter.notifyDataSetChanged();

}



}