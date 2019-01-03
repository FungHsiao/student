package com.example.yt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentActivity extends AppCompatActivity {
    EditText edtTxt_number,edtTxt_name;
    Button btn_query,btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        edtTxt_number=findViewById(R.id.edtTxt_student_number);
        edtTxt_name=findViewById(R.id.edtTxt_student_name);
        btn_query=findViewById(R.id.btn_student_query);
        btn_return=findViewById(R.id.btn_student_return);

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
