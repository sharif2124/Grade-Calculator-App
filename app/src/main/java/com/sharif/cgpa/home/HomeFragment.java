package com.sharif.cgpa.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sharif.cgpa.DataController;
import com.sharif.cgpa.GradeRepository;
import com.sharif.cgpa.R;
import com.sharif.cgpa.model.Semister;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeFragmentInterface {
View root;
GradeRepository repository;
RecyclerView recyclerView;
HomeRecyclerAdapter adapter;
List<Semister> allSemister = new ArrayList<>();
DataController controller;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

      root=inflater.inflate(R.layout.fragment_home, container, false);
      repository=new GradeRepository(getActivity().getApplication());
      recyclerView=root.findViewById(R.id.home_recylerview);
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      allSemister=repository.GetAllSemister();
      adapter = new HomeRecyclerAdapter(allSemister);
      recyclerView.setAdapter(adapter);
      controller=DataController.getInstance();
      controller.setHomeFragmentInterface(this);


        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_user_input_dialog);
                EditText SemesterNameEdittext = dialog.findViewById(R.id.dialog_semester_edittext);
                Button createbutton = dialog.findViewById(R.id.dialog_create_semester);
                createbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(SemesterNameEdittext.getText().equals("")){
                            Toast.makeText(getActivity(), "Please Insert Semester Name", Toast.LENGTH_LONG).show();

                        }else {
                            String SemesterName = SemesterNameEdittext.getText().toString();
                            Toast.makeText(getActivity(), SemesterName+ "Is SemesterName", Toast.LENGTH_LONG).show();
                            insertSemister(SemesterName);
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });

      return root;
    }
private void insertSemister(String semisterName){
    Semister temp=new Semister(semisterName,0.0);
    allSemister.add(temp);
    adapter.notifyDataSetChanged();
    repository.InsertSemister(temp);

}


    @Override
    public void onSemisterItemClick(Semister semister) {
        controller.setCurrentSemister(semister);
        NavHostFragment.findNavController(HomeFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment);
    }
}