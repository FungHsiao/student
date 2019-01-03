package com.example.yt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryActivity extends AppCompatActivity {
    EditText edtTxt_number,edtTxt_name,edtTxt_course;
    Button btn_query,btn_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        edtTxt_number=findViewById(R.id.edtTxt_query_number);
        edtTxt_name=findViewById(R.id.edtTxt_query_name);
        edtTxt_course=findViewById(R.id.edtTxt_query_course);
        btn_query=findViewById(R.id.btn_query_query);
        btn_return=findViewById(R.id.btn_query_return);

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=edtTxt_number.getText().toString().trim();
                String name=edtTxt_name.getText().toString().trim();
                String course=edtTxt_course.getText().toString().trim();
                MySQLiteAdapter adapter=new MySQLiteAdapter(getApplicationContext(),"database.db");
                List<Infor> list=new ArrayList<>();
                if(!name.equals("")) {
                    if (!course.equals("")) {
                        list = adapter.query(number,name, course);
                    } else {
                        list = adapter.queryBynameNo(number,name);
                    }
                }else{
                    if(!course.equals("")){
                        list=adapter.queryByCourse(course);
                    }else{
                        list=adapter.queryAll();
                    }
                }
                /*Iterator<Infor> iter=list.iterator();
                tv_display.setText(null);
                while (iter.hasNext()){
                    Infor infor=iter.next();
                    tv_display.append("   "+infor.getName()+"   ");
                    tv_display.append(infor.getSubject()+"   ");
                    tv_display.append(String.valueOf(infor.getScore()));
                    tv_display.append("\n");
                }*/

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
