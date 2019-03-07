package com.onesourceweb.dbdemo.database;

public class Student {

    public static final String TABLE_NAME = "student";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHNO = "phno";

    private int id;
    private String name;
    private String phno;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_PHNO + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Student() {
    }

    public Student(int id, String name, String phno) {
        this.id = id;
        this.name = name;
        this.phno = phno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
