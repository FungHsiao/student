package com.example.yt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class QueryActivity extends AppCompatActivity {
    EditText edtTxt_name,edtTxt_course;
    Button btn_query,btn_all,btn_home;
    ListView lv_display;
    List<Infor> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        edtTxt_name=findViewById(R.id.edtTxt_query_name);
        edtTxt_course=findViewById(R.id.edtTxt_query_course);
        btn_query=findViewById(R.id.btn_query_query);
        lv_display=findViewById(R.id.lv_query_display);
        btn_all=findViewById(R.id.btn_query_all);
        btn_home=findViewById(R.id.btn_query_home);


        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QueryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQLiteAdapter adapter=new MySQLiteAdapter(getApplicationContext(),"database.db");
                list=adapter.queryAll();
                edtTxt_name.setText(null);
                edtTxt_course.setText(null);
                MyBasedAdapter listadapter=new MyBasedAdapter(list);
                lv_display.setAdapter(listadapter);
            }
        });

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtTxt_name.getText().toString().trim();
                String course = edtTxt_course.getText().toString().trim();
                if (name.equals("") && course.equals("")) {
                    Toast.makeText(getApplicationContext(), "姓名与科目不能同时为空", Toast.LENGTH_SHORT).show();
                } else {
                    MySQLiteAdapter adapter = new MySQLiteAdapter(getApplicationContext(), "database.db");
                    if (!name.equals("")) {
                        if (!course.equals("")) {
                            list = adapter.query(name, course);
                        } else {
                            list = adapter.queryByname(name);
                        }
                    } else {
                        if (!course.equals("")) {
                            list = adapter.queryByCourse(course);
                        }else{
                            list= adapter.queryAll();
                            edtTxt_name.setText(null);
                            edtTxt_course.setText(null);
                        }
                    }
                    MyBasedAdapter listadapter = new MyBasedAdapter(list);
                    lv_display.setAdapter(listadapter);

                }
            }
        });

    }
    class MyBasedAdapter extends BaseAdapter {
        List<Infor> list;
        public MyBasedAdapter(List<Infor>list){
            this.list=list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(QueryActivity.this,R.layout.list_itme_rea,null);

            TextView tv_number=view.findViewById(R.id.tv_listItme_rea_number);
            TextView tv_name=view.findViewById(R.id.tv_listItme_rea_name);
            TextView tv_course=view.findViewById(R.id.tv_listItme_rea_course);
            TextView tv_score=view.findViewById(R.id.tv_listItme_rea_score);

            Infor infor=(Infor) getItem(position);
            tv_number.setText(infor.getNumber());
            tv_name.setText(infor.getName());
            tv_course.setText(infor.getCourse());
            tv_score.setText(String.valueOf(infor.getScore()));

            return view;
        }
    }
}
