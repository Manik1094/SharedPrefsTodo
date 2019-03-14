package com.example.helloworld.sharedprefs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button add_btn , clear_btn;
    TextView title_txt , priority_txt , id_txt;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        add_btn = findViewById(R.id.add_btn);
        title_txt = findViewById(R.id.title_txt);
        priority_txt = findViewById(R.id.priority_txt);
        id_txt = findViewById(R.id.id_txt);
        clear_btn = findViewById(R.id.clear_btn);

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                onStart();
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AddTodoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        preferences = getApplicationContext().getSharedPreferences("TodoPrefs",MODE_PRIVATE);
        editor = preferences.edit();

        retreiveData();
    }

    private void retreiveData() {

        String title = preferences.getString("title" , "none");
        int id = preferences.getInt("id" , 0);
        boolean priority = preferences.getBoolean("priority" , false);

        title_txt.setText("Title : " + title);
        id_txt.setText("Id : " + id);
        if(priority){
            priority_txt.setText("Priority : High" );
        }else {
            priority_txt.setText("Priority : Low");
        }

    }
}
