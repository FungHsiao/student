package com.example.yt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {
    EditText edtTxt_number,edtTxt_name,edtTxt_course,edtTxt_score;
    Button btn_insert,btn_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edtTxt_number=findViewById(R.id.edtTxt_update_number);
        edtTxt_name=findViewById(R.id.edtTxt_update_name);
        edtTxt_course=findViewById(R.id.edtTxt_update_course);
        edtTxt_score=findViewById(R.id.edtTxt_update_score);
        btn_insert=findViewById(R.id.btn_update_update);
        btn_return=findViewById(R.id.btn_insert_return);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InsertActivity.this,TeacherActivity.class);
                startActivity(intent);
            }
        });

    }
}
