package com.example.yt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class delete1 extends AppCompatActivity {

    private ListView lv_main;
    private ArrayList<MyBean> myBeans;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_main = (ListView) findViewById(R.id.lv_main);


        myBeans = new ArrayList<>();
        for(int i=0;i<100;i++){
            myBeans.add(new MyBean("iwanghang~"+i));
        }
        myAdapter = new MyAdapter();
        lv_main.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return myBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                convertView = View.inflate(delete1.this,R.layout.delete2,null);
                viewHolder = new ViewHolder();
                viewHolder.item_content = (TextView) convertView.findViewById(R.id.item_content);
                viewHolder.item_menu = (TextView) convertView.findViewById(R.id.item_menu);

                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // 根据位置得到内容
            final MyBean myBean = myBeans.get(position);
            viewHolder.item_content.setText(myBean.getName());

            viewHolder.item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyBean myBean1 = myBeans.get(position);
                    Toast.makeText(delete1.this, myBean1.getName(), Toast.LENGTH_SHORT).show();
                    System.out.println("MainActivity---onClick");
                }
            });

            viewHolder.item_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /**
                     * getParent() 获取父组件
                     * 获取爷爷组件，可以使用getParent().getParent()
                     */
                    SlideLayout slideLayout = (SlideLayout) view.getParent();
                    slideLayout.closeMenu();
                    myBeans.remove(myBean);
                    notifyDataSetChanged();
                    System.out.println("MainActivity---remove");
                }
            });

            SlideLayout slideLayout = (SlideLayout) convertView;
            slideLayout.setOnStateChangeListener(new MyOnStateChangeListener());
            return convertView;
        }
    }

    private SlideLayout slideLayout;

    class MyOnStateChangeListener implements SlideLayout.OnStateChangeListener {

        @Override
        public void onClose(SlideLayout layout) {
            if(slideLayout == layout){
                slideLayout = null;
            }
        }

        @Override
        public void onDown(SlideLayout layout) {
            if(slideLayout!=null && slideLayout!=layout){
                slideLayout.closeMenu();
            }
        }

        @Override
        public void onOpen(SlideLayout layout) {
            slideLayout = layout;
        }

    }


    static class ViewHolder{
        TextView item_content;
        TextView item_menu;
    }
}

