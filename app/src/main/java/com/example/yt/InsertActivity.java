package com.example.yt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    EditText edtTxt_number,edtTxt_name,edtTxt_course,edtTxt_score;
    Button btn_insert,btn_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edtTxt_number=findViewById(R.id.edtTxt_insert_number);
        edtTxt_name=findViewById(R.id.edtTxt_insert_name);
        edtTxt_course=findViewById(R.id.edtTxt_insert_course);
        edtTxt_score=findViewById(R.id.edtTxt_insert_score);
        btn_insert=findViewById(R.id.btn_insert_update);
        btn_home=findViewById(R.id.btn_insert_home);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InsertActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = edtTxt_number.getText().toString().trim();
                String name = edtTxt_name.getText().toString().trim();
                String course = edtTxt_course.getText().toString().trim();
                String score =edtTxt_score.getText().toString().trim();
                if (number.equals("") || name.equals("") || course.equals("") || score.equals("")) {
                    Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    MySQLiteAdapter adapter = new MySQLiteAdapter(getApplicationContext(), "database.db");
                    Infor infor = new Infor();
                    infor.setNumber(edtTxt_number.getText().toString().trim());
                    infor.setName(edtTxt_name.getText().toString().trim());
                    infor.setCourse(edtTxt_course.getText().toString().trim());
                    infor.setScore(Float.valueOf(edtTxt_score.getText().toString().trim()));
                    long result = adapter.insert(infor);
                    if (result == 0) {
                        Toast.makeText(getApplicationContext(), "该成绩已存在", Toast.LENGTH_SHORT).show();
                    } else if (result > 0) {
                        Toast.makeText(getApplicationContext(), "成绩录入成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "成绩录入失败", Toast.LENGTH_SHORT).show();
                    }
                    edtTxt_course.setText(null);
                    edtTxt_score.setText(null);

                }
            }
        });


    }
}
