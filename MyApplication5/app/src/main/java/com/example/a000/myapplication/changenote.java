package com.example.a000.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a000.myapplication.dao.AccountDao;

public class changenote extends AppCompatActivity {
    private AccountDao Dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changenote);
        Dao = new AccountDao(this);
        Button back = findViewById(R.id.back);
        Button change = findViewById(R.id.change);
        final EditText editText = (EditText) findViewById(R.id.show);
        Intent intent = getIntent();
        final Long ID = intent.getLongExtra("ID", 0);

        editText.setText(Dao.query(ID));
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(changenote.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
        change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = editText.getText().toString().trim();
                       Dao.update(ID , name);
                        Toast.makeText(getBaseContext(), "修改成功,已保存", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(changenote.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }

}
