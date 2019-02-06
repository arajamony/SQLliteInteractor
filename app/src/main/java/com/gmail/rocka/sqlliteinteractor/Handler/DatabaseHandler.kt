package com.gmail.rocka.sqlliteinteractor.Handler

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.gmail.rocka.sqlliteinteractor.Models.User

val DATABASE_NAME="MyDB"
val TABLE_NAME="Users"
val COL_NAME="name"
val COL_AGE="age"
val COL_ID="id"

class DatabaseHandler(context:Context):SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_AGE + " INTEGER)";
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    // Insert the data to DB(SQLLITE)
    fun insertData(user:User):Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, user.name)
        cv.put(COL_AGE, user.age)

        var result = db.insert(TABLE_NAME, null, cv)
        return result
    }

    // Read the data from the sqlLite
    fun ReadData():MutableList<User>{
        var list:MutableList<User> = ArrayList()
        val db=this.readableDatabase
        val querry="SELECT * FROM ${TABLE_NAME}"
        val result=db.rawQuery(querry,null)

        if(result.moveToFirst())
        {
            do {
                var user=User()
                user.id=result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.name=result.getString(result.getColumnIndex(COL_NAME))
                user.age=result.getString(result.getColumnIndex(COL_AGE)).toInt()
                list.add(user)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    //Update data, will increment the age by 1 for all the fields
    fun updateData() {
        val db = this.writableDatabase
        val querry = "SELECT * FROM ${TABLE_NAME}"
        val result = db.rawQuery(querry, null)

        if (result.moveToFirst()) {
            do {
                var cv = ContentValues()
                cv.put(COL_AGE, (result.getInt(result.getColumnIndex(COL_AGE)) + 1))
                db.update(
                    TABLE_NAME, cv, COL_ID + "=? AND " + COL_NAME + "=?",
                    arrayOf(
                        result.getString(result.getColumnIndex(COL_ID)),
                        result.getString(result.getColumnIndex(COL_NAME))
                    )
                )
            } while (result.moveToNext())
        }

        db.close()
    }

    // Delete Data
    fun deleteData() {
        var db = this.writableDatabase
        db.delete(TABLE_NAME, COL_ID + "=?", arrayOf(1.toString())) // to delete all the value pass null to 2nd AND 3RD PARAMETER
        db.close()
    }



}