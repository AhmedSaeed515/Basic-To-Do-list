package com.example.todo_list;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89B7CC"));
        actionBar.setBackgroundDrawable(colorDrawable);
       // Objects.requireNonNull(getSupportActionBar()).hide();

        final TextView name = findViewById(R.id.editTextTextPerson1);
        final TextView desc = findViewById(R.id.editTextTextPerson2);
        name.setText(getIntent().getExtras().getString("Name"));
        desc.setText(getIntent().getExtras().getString("Desc"));
        Button back = findViewById(R.id.button1);
        back.setBackgroundColor(Color.parseColor("#89B7CC"));

        back.setOnClickListener(view -> {
            Intent intent = new Intent(description.this, MainActivity.class);
            startActivity(intent);
        });
    }
}

