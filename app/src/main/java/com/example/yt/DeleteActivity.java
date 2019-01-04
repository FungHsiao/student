package com.example.yt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DeleteActivity extends AppCompatActivity {
    TextView tv_display;
    EditText edtTxt_number,edtTxt_name,edtTxt_course;
    Button btn_all,btn_delete,btn_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        tv_display=findViewById(R.id.tv_delete_display);
        edtTxt_number=findViewById(R.id.edtTxt_delete_number);
        edtTxt_name=findViewById(R.id.edtTxt_delete_name);
        edtTxt_course=findViewById(R.id.edtTxt_delete_course);
        btn_all=findViewById(R.id.btn_delete_all);
        btn_delete=findViewById(R.id.btn_delete_delete);
        btn_return=findViewById(R.id.btn_delete_return);

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog dia=new deleteDialog(DeleteActivity.this,"该操作将无法恢复，请谨慎使用！");
                dia.show();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=edtTxt_number.getText().toString().trim();
                String name=edtTxt_name.getText().toString().trim();
                String course=edtTxt_course.getText().toString().trim();
                MySQLiteAdapter adapter=new MySQLiteAdapter(getApplicationContext(),"database.db");
                Log.i("number",number);
                Log.i("name",name);
                Log.i("course",course);
                adapter.delete(number,name,course);

            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DeleteActivity.this,TeacherActivity.class);
                startActivity(intent);
            }
        });

    }
    public class deleteDialog extends Dialog {
        Button btn_ok,btn_cancel;
        String dialogName;
        public deleteDialog(Context context, String dialogName){
            super(context);
            this.dialogName=dialogName;
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_delete_dialog);
            btn_ok=findViewById(R.id.btn_dialog_ok);
            btn_cancel=findViewById(R.id.btn_dialog_cancel);

            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MySQLiteAdapter adapter=new MySQLiteAdapter(getApplicationContext(),"database.db");
                    adapter.deleteAll();
                    dismiss();
                }
            });

        }

    }
}
