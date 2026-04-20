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

public class updateTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89B7CC"));
        actionBar.setBackgroundDrawable(colorDrawable);
        // Objects.requireNonNull(getSupportActionBar()).hide();
        final EditText name = findViewById(R.id.editTextTextPersonNameE1);
        final EditText desc = findViewById(R.id.editTextTextPersonNameE2);
        final database Data = new database(this);
        name.setText(getIntent().getExtras().getString("taskname"));
        desc.setText(getIntent().getExtras().getString("taskdesc"));
        Button update = findViewById(R.id.buttonN);
        update.setBackgroundColor(Color.parseColor("#89B7CC"));
        update.setOnClickListener(view -> {
            Data.UpdateTask(getIntent().getExtras().getString("taskname"),name.getText().toString(),desc.getText().toString());
            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(updateTask.this, MainActivity.class);
            startActivity(intent);
        });
    }
}