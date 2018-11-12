package com.example.a000.myapplication;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a000.myapplication.dao.Account;
import com.example.a000.myapplication.dao.AccountDao;

public class MainActivity extends Activity {

    private List<Account> list;
    private AccountDao Dao;
    private ListView account;
    private MyAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=(ListView) findViewById(R.id.view);
        registerForContextMenu(listView);
        initView();
        Dao = new AccountDao(this);
        //查询数据
        list = Dao.queryAll();
        Log.v("len",list.size()+"");
        adapter = new MyAdapter();
        account.setAdapter(adapter);


        Button add=(Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,newnote.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private class MyAdapter extends BaseAdapter {
        public int getCount() { // 获取条目总数
            return list.size();
        }
        public Object getItem(int position) { // 获取对象
            return list.get(position);
        }
        public long getItemId(int position) { // 获取id
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent) {

            View item = null;
            if (convertView != null) {
                item = convertView;
            } else {
                item = View.inflate(getApplicationContext(), R.layout.item,
                        null);
            }
            TextView name = (TextView) item.findViewById(R.id.name);
            final Account a = list.get(position);
            name.setText(a.getName());

            ImageView delete = (ImageView) item.findViewById(R.id.delete);

            // 删除的点击事件触发的方法
            delete.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    //弹出一个对话框
                    android.content.DialogInterface.OnClickListener listener = new android.content.DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            list.remove(a); // 从集合中删除
                            Dao.delete(a.getId()); // 从数据库中删除
                            notifyDataSetChanged();// 刷新界面
                        }
                    };

                    //对话框
                    Builder builder = new Builder(MainActivity.this);
                    builder.setTitle("确定要删除吗?");
                    builder.setPositiveButton("确定", listener);
                    builder.setNegativeButton("取消", null);
                    builder.show();
                }
            });
            return item;
        }
    }


    private void initView() {
        account = (ListView) findViewById(R.id.view);
        account.setOnItemClickListener(new MyOnItemClickListener());
    }

    //浮动菜单
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_countent,menu);
    }
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.see:
                Toast.makeText(this,"我是一个菜单啊",Toast.LENGTH_SHORT).show();

                break;
        }
        return super.onContextItemSelected(item);
    }

    // ListView的Item点击事件
    private class MyOnItemClickListener implements OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // 获取点击位置上的数据
            Intent intent = new Intent(MainActivity.this, changenote.class);
            Account a = (Account) parent.getItemAtPosition(position);
            Long ID = a.getId();
            // String text= a.toString();
            intent.putExtra("ID", ID);
            startActivity(intent);
            finish();
        }
    }

}
