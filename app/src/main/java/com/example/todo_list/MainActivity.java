package com.example.todo_list;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;






public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> listAdapter;
    database data;
    ListView mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Objects.requireNonNull(getSupportActionBar()).hide();
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89B7CC"));
        actionBar.setBackgroundDrawable(colorDrawable);
        mylist = findViewById(R.id.ListView);
        listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1);
        registerForContextMenu(mylist);
        data=new database(getApplicationContext());
        mylist.setAdapter(listAdapter);
        Cursor cursor =data.fetchallTask();
        while (!cursor.isAfterLast())
        {
            listAdapter.add(cursor.getString(0));
            cursor.moveToNext();
        }

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Intent desc = new Intent(MainActivity.this,description.class);
                desc.putExtra("Name",selectedItem);
                desc.putExtra("Desc",data.getTaskdesc(selectedItem));
                startActivity(desc);
            }
        });

        FloatingActionButton add = findViewById(R.id.floatingActionButton2);
        add.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,addTaskScreen.class);
            startActivity(intent);
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.taskmenu,menu);
        menu.setHeaderTitle("Select One");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info
                =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String selectedTask = ((TextView)info.targetView).getText().toString();
        if(item.getItemId() == R.id.subitem11)//edit
        {
            Intent i = new Intent(MainActivity.this,updateTask.class);
            i.putExtra("taskname",selectedTask);
            i.putExtra("taskdesc",data.getTaskdesc(selectedTask));
            startActivity(i);
            return true;
        }
        else if(item.getItemId() == R.id.subitem12)//Delete
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Delete entry");
            alert.setMessage("Are you sure you want to delete?");
            alert.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                // continue with delete
                listAdapter.remove(selectedTask);
                data.DeleteTask(selectedTask);
            });
            alert.setNegativeButton(android.R.string.no, (dialog, which) -> {
                // close dialog
                dialog.cancel();
            });
            alert.show();
            return true;
        }
        else{
            return false;
        }
    }


}