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

public class UpdateActivity extends AppCompatActivity {
    ListView lv_display;
    List<Infor> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        lv_display=findViewById(R.id.lv_update_display);



        MySQLiteAdapter adapter=new MySQLiteAdapter(getApplicationContext(),"database.db");
        list=adapter.queryAll();
        MyBasedAdapter listadapter=new MyBasedAdapter(list);
        lv_display.setAdapter(listadapter);

        /*btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = edtTxt_number.getText().toString().trim();
                String name = edtTxt_name.getText().toString().trim();
                String course = edtTxt_course.getText().toString().trim();
                String score1=edtTxt_score.getText().toString().trim();
                if (number.equals("") && name.equals("") && course.equals("") && score1.equals("")) {
                    Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_SHORT).show();
                }
                    else {
                    Float score = Float.valueOf(edtTxt_score.getText().toString().trim());
                    Infor infor = new Infor();
                    infor.setNumber(number);
                    infor.setName(name);
                    infor.setCourse(course);
                    infor.setScore(score);
                    MySQLiteAdapter adapter = new MySQLiteAdapter(getApplicationContext(), "database.db");
                    adapter.update(infor);
                    list = adapter.queryAll();
                    MyBasedAdapter listadapter = new MyBasedAdapter(list);
                    lv_display.setAdapter(listadapter);

                }
            }
        });*/
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
            View view=View.inflate(UpdateActivity.this,R.layout.list_update,null);

            TextView tv_number=view.findViewById(R.id.edt_list_update_number);
            TextView tv_name=view.findViewById(R.id.edt_list_update_name);
            TextView tv_course=view.findViewById(R.id.edt_list_update_course);
            TextView tv_score=view.findViewById(R.id.edt_list_update_score);

            Infor infor=(Infor) getItem(position);
            tv_number.setText(infor.getNumber());
            tv_name.setText(infor.getName());
            tv_course.setText(infor.getCourse());
            tv_score.setText(String.valueOf(infor.getScore()));

            return view;
        }
    }
}
