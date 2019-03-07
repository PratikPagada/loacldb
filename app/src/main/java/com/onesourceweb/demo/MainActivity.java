package com.onesourceweb.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.onesourceweb.dbdemo.database.DatabaseHelper;
import com.onesourceweb.dbdemo.database.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtname, edtphno;
    Button submit;

    private DatabaseHelper db;
    RecyclerView recyclerView;
    private Studentadapter mAdapter;
    List<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtname = findViewById(R.id.edt_name);
        edtphno = findViewById(R.id.edt_phno);
        submit = findViewById(R.id.btn_sbmt);
        recyclerView = findViewById(R.id.recycler_view);
        db = new DatabaseHelper(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new Studentadapter(MainActivity.this, students);
        recyclerView.setAdapter(mAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtname.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtphno.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter PhNo!", Toast.LENGTH_SHORT).show();
                } else {
                    students.add(db.insertStudent(edtname.getText().toString(), edtphno.getText().toString()));
                    mAdapter.notifyDataSetChanged();
                    edtname.setText("");
                    edtphno.setText("");
                }
            }
        });

    }
}
