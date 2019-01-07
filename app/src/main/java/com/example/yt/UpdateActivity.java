package com.example.yt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
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
    private  DeletBean deletBean;
    private MyBasedAdapter listadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        lv_display=findViewById(R.id.lv_update_display);


        lv_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Infor infor = list.get(position);
                deletBean=new DeletBean(infor.getNumber(),infor.getName(),infor.getCourse());
                UpdateDialogActivity updialog=new UpdateDialogActivity(UpdateActivity.this,"");
                updialog.show();
            }
        });

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
            View view=View.inflate(UpdateActivity.this,R.layout.list_itme_rea,null);

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
    public class UpdateDialogActivity extends Dialog {

        Button btn_ok, btn_cancel;
        EditText edt_score;
        private String dialogName;

        public UpdateDialogActivity(Context context, String dialogName) {
            super(context);
            this.dialogName = dialogName;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_update_dialog);
            btn_ok = findViewById(R.id.btn__update_dialog_ok);
            btn_cancel = findViewById(R.id.btn_update_dialog_cancel);
            edt_score=findViewById(R.id.edt_update_dialog_score);


            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((edt_score.getText().toString().trim()).equals("")){
                        Toast.makeText(getApplicationContext(), "请输入有效值", Toast.LENGTH_SHORT).show();
                    }else {
                        Float score = Float.valueOf(edt_score.getText().toString().trim());
                        Infor infor = new Infor();
                        infor.setNumber(deletBean.getNumb());
                        infor.setName(deletBean.getName());
                        infor.setCourse(deletBean.getCoures());
                        infor.setScore(score);
                        MySQLiteAdapter adapter = new MySQLiteAdapter(getApplicationContext(), "database.db");
                        adapter.update(infor);
                        list = adapter.queryAll();
                        MyBasedAdapter listadapter = new MyBasedAdapter(list);
                        lv_display.setAdapter(listadapter);
                        dismiss();
                    }
                }
            });


        }
    }
}
