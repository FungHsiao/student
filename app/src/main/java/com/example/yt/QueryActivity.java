package com.example.yt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QueryActivity extends AppCompatActivity {
    EditText edtTxt_number,edtTxt_name,edtTxt_course;
    Button btn_query,btn_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        edtTxt_number=findViewById(R.id.edtTxt_update_number);
        edtTxt_name=findViewById(R.id.edtTxt_update_name);
        edtTxt_course=findViewById(R.id.edtTxt_update_course);
        btn_query=findViewById(R.id.btn_query_query);
        btn_return=findViewById(R.id.btn_query_return);

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QueryActivity.this,TeacherActivity.class);
                startActivity(intent);
            }
        });
    }
}
