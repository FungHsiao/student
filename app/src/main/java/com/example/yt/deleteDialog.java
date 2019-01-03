package com.example.yt;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
public class deleteDialog extends Dialog{
    Button btn_ok,btn_cancel;
    String dialogName;
    public deleteDialog(Context context,String dialogName){
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

            }
        });

    }

}
