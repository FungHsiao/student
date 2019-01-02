package com.example.yt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_student,btn_teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_student=findViewById(R.id.btn_main_student);
        btn_teacher=findViewById(R.id.btn_main_teacher);

        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,StudentActivity.class);
                startActivity(intent);
            }
        });
        btn_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,TeacherActivity.class);
                startActivity(intent);
            }
        });
    }

}
