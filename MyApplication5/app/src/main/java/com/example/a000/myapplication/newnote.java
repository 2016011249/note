package com.example.a000.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.a000.myapplication.dao.Account;
import com.example.a000.myapplication.dao.AccountDao;

public class newnote extends AppCompatActivity {
    private AccountDao Dao;
    // 输入便签的内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnote);
        Dao = new AccountDao(this);
        Button back = findViewById(R.id.back);
        Button add = findViewById(R.id.add);
        final EditText editText = (EditText) findViewById(R.id.show);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(newnote.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = editText.getText().toString();
                        Account a = new Account(name);
                        Dao.insert(a);
                        Intent intent = new Intent(newnote.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }


}
