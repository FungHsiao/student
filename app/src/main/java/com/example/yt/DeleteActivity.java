package com.example.yt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    ListView lv_display;
    Button btn_all,btn_home;
    List<Infor> list;
    private  DeletBean deletBean;
    private  MyBasedAdapter listadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        lv_display = findViewById(R.id.lv_delete_display);
        btn_all = findViewById(R.id.btn_delete_all);
        btn_home=findViewById(R.id.btn_delete_home);

        MySQLiteAdapter adapter = new MySQLiteAdapter(getApplicationContext(), "database.db");
        list = adapter.queryAll();

         listadapter = new MyBasedAdapter(list);
        lv_display.setAdapter(listadapter);

        lv_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Infor infor = list.get(position);
                deletBean=new DeletBean(infor.getNumber(),infor.getName(),infor.getCourse());
                delete_one_dialogActivity done = new delete_one_dialogActivity(DeleteActivity.this, "确定要删除该成绩吗");
                done.show();


            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DeleteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog dia = new deleteDialog(DeleteActivity.this, "该操作将无法恢复，请谨慎使用！");
                dia.show();
            }
        });

    }

    public class deleteDialog extends Dialog {
        Button btn_ok, btn_cancel;
        private String dialogName;

        public deleteDialog(Context context, String dialogName) {
            super(context);
            this.dialogName = dialogName;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_delete_dialog);
            btn_ok = findViewById(R.id.btn_dialog_ok);
            btn_cancel = findViewById(R.id.btn_dialog_cancel);


            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MySQLiteAdapter adapter = new MySQLiteAdapter(getApplicationContext(), "database.db");
                    adapter.deleteAll();
                    list = adapter.queryAll();
                    MyBasedAdapter listadapter = new MyBasedAdapter(list);
                    lv_display.setAdapter(listadapter);
                    dismiss();
                }
            });


        }

    }

    class MyBasedAdapter extends BaseAdapter {
        List<Infor> list;

        public MyBasedAdapter(List<Infor> list) {
            this.list = list;
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
            View view = View.inflate(DeleteActivity.this, R.layout.list_itme_rea, null);

            TextView tv_number = view.findViewById(R.id.tv_listItme_rea_number);
            TextView tv_name = view.findViewById(R.id.tv_listItme_rea_name);
            TextView tv_course = view.findViewById(R.id.tv_listItme_rea_course);
            TextView tv_score = view.findViewById(R.id.tv_listItme_rea_score);

            Infor infor = (Infor) getItem(position);
            tv_number.setText(infor.getNumber());
            tv_name.setText(infor.getName());
            tv_course.setText(infor.getCourse());
            tv_score.setText(String.valueOf(infor.getScore()));

            return view;
        }
    }

    public class delete_one_dialogActivity extends Dialog {

        Button btn_ok, btn_cancel;
        private String dialogNameone;

        public delete_one_dialogActivity(Context context, String dialogNameone) {
            super(context);
            this.dialogNameone = dialogNameone;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_delete_one_dialog);
            btn_ok = findViewById(R.id.btn_one_ok);
            btn_cancel = findViewById(R.id.btn_one_cancel);

            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MySQLiteAdapter adapter = new MySQLiteAdapter(getApplicationContext(), "database.db");
                    adapter.delete(deletBean.getNumb(),deletBean.getName(),deletBean.getCoures());
                    list = adapter.queryAll();
                    MyBasedAdapter listadapter=new MyBasedAdapter(list);
                    lv_display.setAdapter(listadapter);
                    dismiss();
                }
            });


        }

    }
}
