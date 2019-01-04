package com.example.yt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeacherActivity extends AppCompatActivity {
    Button btn_insert,btn_query,btn_update,btn_delete,btn_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        btn_insert=findViewById(R.id.btn_teacher_insert);
        btn_query=findViewById(R.id.btn_teacher_query);
        btn_update=findViewById(R.id.btn_teacher_update);
        btn_delete=findViewById(R.id.btn_teacher_delete);
        btn_return=findViewById(R.id.btn_teacher_return);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherActivity.this,InsertActivity.class);
                startActivity(intent);
            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherActivity.this,QueryActivity.class);
                startActivity(intent);
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherActivity.this,UpdateActivity.class);
                startActivity(intent);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherActivity.this,DeleteActivity.class);
                startActivity(intent);
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
