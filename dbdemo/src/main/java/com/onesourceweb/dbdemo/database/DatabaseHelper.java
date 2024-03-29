package com.onesourceweb.dbdemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "student_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create student table
        db.execSQL(Student.CREATE_TABLE);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    //Add Student Details

    public Student insertStudent(String sName, String phNo) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Student.COLUMN_NAME, sName);
        values.put(Student.COLUMN_PHNO, phNo);

        // insert row
        long id = db.insert(Student.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return getStudent(id);
    }

    //Fetch All Student

    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();

        // Select All Query
 /*       String selectQuery = "SELECT  * FROM " + Student.TABLE_NAME + " ORDER BY " +
                Student.COLUMN_NAME + " DESC";*/

        String selectQuery = "SELECT  * FROM " + Student.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student sd = new Student();
                sd.setId(cursor.getInt(cursor.getColumnIndex(Student.COLUMN_ID)));
                sd.setName(cursor.getString(cursor.getColumnIndex(Student.COLUMN_NAME)));
                sd.setPhno(cursor.getString(cursor.getColumnIndex(Student.COLUMN_PHNO)));

                students.add(sd);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return students;
    }

    // Update Student

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Student.COLUMN_NAME, student.getName());

        // updating row
        return db.update(Student.TABLE_NAME, values, Student.COLUMN_ID + " = ?",
                new String[]{String.valueOf(student.getId())});
    }

    //Deleting Student Datal

    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Student.TABLE_NAME, Student.COLUMN_ID + " = ?",
                new String[]{String.valueOf(student.getId())});
        db.close();
    }

    public Student getStudent(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Student.TABLE_NAME,
                new String[]{Student.COLUMN_ID, Student.COLUMN_NAME, Student.COLUMN_PHNO},
                Student.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Student student = new Student(
                cursor.getInt(cursor.getColumnIndex(Student.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Student.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Student.COLUMN_PHNO)));

        // close the db connection
        cursor.close();

        return student;
    }

    public int getStudentCount() {
        String countQuery = "SELECT  * FROM " + Student.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
}