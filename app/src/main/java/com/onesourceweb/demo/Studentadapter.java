package com.onesourceweb.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onesourceweb.dbdemo.database.Student;

import java.util.List;

public class Studentadapter extends RecyclerView.Adapter<Studentadapter.MyViewHolder> {

    private Context context;
    private List<Student> students;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView phno;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_name);
            phno = view.findViewById(R.id.tv_phno);
        }
    }


    public Studentadapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowiteam, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Student student = students.get(position);

        holder.name.setText(student.getName());
        holder.phno.setText(student.getPhno());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

}