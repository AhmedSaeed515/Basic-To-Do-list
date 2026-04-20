package com.example.todo_list;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    private  static final String Taskdb ="TaskDatabase";
    SQLiteDatabase TaskDatabase;
    public database(Context context)
    {
        super(context,Taskdb,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table task(id integer primary key,name text not null , description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldV, int NewV) {
    db.execSQL("drop table if exists task");
    onCreate(db);
    }

    public void Newtask(String name , String Desc)
    {
        ContentValues row = new ContentValues();
        row.put("name",name);
        row.put("description",Desc);
        TaskDatabase = getWritableDatabase();
        TaskDatabase.insert("task",null,row);
        TaskDatabase.close();
    }
    public Cursor fetchallTask()
    {
        TaskDatabase=getReadableDatabase();
        String[] rowDetails={"name","description","id"};
        Cursor cursor = TaskDatabase.query("task",rowDetails,null,null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        TaskDatabase.close();
        return cursor;
    }
    public String getTaskdesc(String name)
    {
        TaskDatabase=getReadableDatabase();
        String[] arg={name};
        Cursor cursor = TaskDatabase.rawQuery("Select description from task Where name Like ?",arg);
        cursor.moveToFirst();
        TaskDatabase.close();
        return cursor.getString(0);
    }

    public Cursor getTaskdata(int id)
    {
     TaskDatabase=getReadableDatabase();
     Cursor cursor = TaskDatabase.query("task",
             new String[]{"name","description"},"id="+id,null,null,null,null);
     cursor.moveToFirst();
     TaskDatabase.close();
     return cursor;
    }

    public void DeleteTask(String name)
    {
        TaskDatabase=getWritableDatabase();
        TaskDatabase.delete("task","name='"+ name +"'",null);
        TaskDatabase.close();
    }

    public void UpdateTask(String oldname,String newname,String Newdesc)
    {
        TaskDatabase=getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put("name",newname);
        row.put("description",Newdesc);
        TaskDatabase.update("task",row,"name like ?",new String[]{oldname});
        TaskDatabase.close();
    }


}
