package com.example.helloworld.sharedprefs;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddTodoActivity extends AppCompatActivity {

    EditText title_et , id_et;
    Button add_todo_btn;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean priority = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
      //  preferences = getApplicationContext().getSharedPreferences("TodoPrefs" , MODE_PRIVATE);
        editor = preferences.edit();

        title_et = findViewById(R.id.title_et);
        id_et = findViewById(R.id.id_et);
        add_todo_btn = findViewById(R.id.add_todo_btn);

        add_todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addTodoToPrefs();
                finish();
            }
        });
    }

    private void addTodoToPrefs() {

        editor.putString("title" , title_et.getText().toString().trim());
        editor.putBoolean("priority" , priority);
        editor.putInt("id" , Integer.parseInt(id_et.getText().toString()));

        editor.commit();

    }

    public void onRadioButtonClicked(View view){
        boolean isChecked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.low_rb : if(isChecked){

                                   priority = false;
                                    }
                                break;

            case R.id.high_rb : if(isChecked){

                priority = true;
            }
            break;

        }
    }
}
