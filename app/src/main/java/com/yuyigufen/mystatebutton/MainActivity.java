package com.yuyigufen.mystatebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyStateButtonClickListener{
MyStateButton button1,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.mybt);
        button2=findViewById(R.id.mybt2);
        button3=findViewById(R.id.mybt3);
        button1.setMyStateButtonClickListener(this);
        button2.setMyStateButtonClickListener(this);
        button3.setMyStateButtonClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mybt:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mybt2:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mybt3:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
