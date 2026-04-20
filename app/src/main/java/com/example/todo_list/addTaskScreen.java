package com.example.todo_list;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addTaskScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_screen);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89B7CC"));
        actionBar.setBackgroundDrawable(colorDrawable);
       // Objects.requireNonNull(getSupportActionBar()).hide();
        Button Save = findViewById(R.id.button);
        Save.setBackgroundColor(Color.parseColor("#89B7CC"));

        final EditText name = findViewById(R.id.editTextTextPersonName1);
        final EditText desc = findViewById(R.id.editTextTextPersonName2);
        final database Data = new database(this);
        Save.setOnClickListener(view -> {
            Data.Newtask(name.getText().toString(),desc.getText().toString());
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(addTaskScreen.this, MainActivity.class);
            startActivity(intent);
        });


    }
}