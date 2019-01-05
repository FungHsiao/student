package com.example.yt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class StuQueryActivity extends AppCompatActivity {
    TextView tv_number,tv_name;
    ListView lv_display;
    List<Infor> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_query);

        tv_number=findViewById(R.id.tv_stu_number);
        tv_name=findViewById(R.id.tv_stu_name);
        lv_display=findViewById(R.id.lv_stu_display);

        Intent intent=getIntent();
        String number=intent.getStringExtra("number");
        String name=intent.getStringExtra("name");

        tv_number.setText(number);
        tv_name.setText(name);

        MySQLiteAdapter adapter=new MySQLiteAdapter(getApplicationContext(),"database.db");
        list=adapter.queryBynameNo(number,name);
        MyBasedAdapter listadapter=new MyBasedAdapter(list);
        lv_display.setAdapter(listadapter);
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
            View view=View.inflate(StuQueryActivity.this,R.layout.list_itme,null);
            TextView tv_course=view.findViewById(R.id.tv_listItme_course);
            TextView tv_score=view.findViewById(R.id.tv_listItme_score);


            Infor infor=(Infor) getItem(position);
            tv_course.setText(infor.getCourse());
            tv_score.setText(String.valueOf(infor.getScore()));

            return view;
        }
    }
}
