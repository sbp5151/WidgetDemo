package com.boping.widgetDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button list = (Button) findViewById(R.id.list);
        list.setOnClickListener(this);
        Button gird = (Button) findViewById(R.id.grid);
        gird.setOnClickListener(this);
        Button stagger = (Button) findViewById(R.id.stagger);
        stagger.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        Class myClass = null;
        switch (id) {
            case R.id.list:
                myClass = ListActivity.class;
                break;
            case R.id.grid:
                myClass = GridActivity.class;
                break;
            case R.id.stagger:
                myClass = StaggerActivity.class;
                break;
        }
        Intent intent = new Intent(this, myClass);
        startActivity(intent);
    }
}
