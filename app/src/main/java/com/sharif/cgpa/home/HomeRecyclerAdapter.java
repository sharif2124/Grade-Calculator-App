package com.sharif.cgpa.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharif.cgpa.DataController;
import com.sharif.cgpa.R;
import com.sharif.cgpa.model.Semister;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.viewHolder> {
List<Semister>mySemisterList;

    public HomeRecyclerAdapter(List<Semister> mySemisterList) {
        this.mySemisterList = mySemisterList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.semister_recycler_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
  Semister CurrentSemister = mySemisterList.get(position);
  holder.SemisterNameTextView.setText(CurrentSemister.getSemisterName());
  holder.SemisterCreditTextView.setText("Credit : "+CurrentSemister.getSemisterCredit()+"");

    }

    @Override
    public int getItemCount() {
        if(mySemisterList==null||mySemisterList.isEmpty()){
            return 0;
        }
        else return mySemisterList.size();

    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

TextView SemisterNameTextView,SemisterCreditTextView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            SemisterNameTextView=itemView.findViewById(R.id.semistername_item_textview);
            SemisterCreditTextView=itemView.findViewById(R.id.semistercredit_item_textview);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            DataController.instance.getHomeFragmentInterface().onSemisterItemClick(mySemisterList.get(getAdapterPosition()));
        }
    }


}
