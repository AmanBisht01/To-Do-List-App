package com.aman.to_dolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotesHandler extends  Databasehelper{
    public NotesHandler(Context context) {
        super(context);
    }

    //CRUD
    public boolean create(Notes notes){
        ContentValues contentValues=new ContentValues();
        contentValues.put("title", notes.getTitle());
        contentValues.put("description",notes.getDescription());

        SQLiteDatabase db=this.getWritableDatabase();
        boolean isSucessful=db.insert("Notes", null, contentValues)>0;
        db.close();
        return isSucessful;

    }

     public ArrayList<Notes> readNotes(){
        ArrayList<Notes> notes =new  ArrayList<>();
        String sqlQuary="SELECT *FROM Notes  ORDER BY ID ASC";
        SQLiteDatabase db=this.getWritableDatabase();
         Cursor cursor=db.rawQuery(sqlQuary, null);
         if(cursor.moveToFirst()){
            do{
                int id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String description=cursor.getString(cursor.getColumnIndex("description"));
                Notes note=new Notes(title,description);
                note.setId(id);
                notes.add(note);

            }while (cursor.moveToNext());

            cursor.close();
            db.close();

         }
        return  notes;


     }

     public Notes readSingleNote(int id){
        Notes note=null;
        String sqlQuary="SELECT *FROM Notes WHERE id="+id;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sqlQuary, null);
        if (cursor.moveToFirst()){
            int noteid=Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String description=cursor.getString(cursor.getColumnIndex("description"));
            note=new Notes(title,description);
            note.setId(noteid);
        }
        cursor.close();
        db.close();
        return note;


     }

     public boolean update(Notes note){
        ContentValues values=new ContentValues();
        values.put("title", note.getTitle());
        values.put("description", note.getDescription());
        SQLiteDatabase db=this.getWritableDatabase();
        boolean issucessful =db.update("Notes", values, "id='"+note.getId()+"'",null)>0;
        db.close();
        return  issucessful;
//         return true;
     }

     public Boolean delete(int id){
        boolean isdelete;
        SQLiteDatabase db=this.getWritableDatabase();

        isdelete=db.delete("Notes", "id ='"+ id +"'",null) >0;
        db.close();
        return isdelete;
     }
}
