package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseRecord.*;
import database.DatabaseContract.*;
/**
 * Created by STUDIO on 13/12/2015.
 */
public class DatabaseDataSource {
    private Context context;
    private  SQLiteDatabase SQLiteDatabase;
    private  DatabaseSQLHelper dbHelper;

    public DatabaseDataSource(Context context){
        this.context = context;
        dbHelper = new DatabaseSQLHelper(context);
    }
    public void open() throws SQLException {
        SQLiteDatabase = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    private String[] UserAllColumn = {
            DatabaseContract.User.USER_COLUMN_ID,
            DatabaseContract.User.USER_COLUMN_USERNAME,
            DatabaseContract.User.USER_COLUMN_PASSWORD,

            DatabaseContract.User.USER_COLUMN_NAME,
            DatabaseContract.User.USER_COLUMN_AGE,
            DatabaseContract.User.USER_COLUMN_DATEOFBIRTH,

            DatabaseContract.User.USER_COLUMN_EMAIL,
            DatabaseContract.User.USER_COLUMN_WEIGHT,
            DatabaseContract.User.USER_COLUMN_HEIGHT,

            DatabaseContract.User.USER_COLUMN_SENSITIVE_FOOD,
            DatabaseContract.User.USER_COLUMN_HEARTRATE,
            DatabaseContract.User.USER_COLUMN_REGISTERDATE
    };


    public  void insertUserRecord(UserRecord User){
        Toast.makeText(context, "insert user id = " + User.getID(), Toast.LENGTH_LONG).show();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.User.USER_COLUMN_ID, User.getID());
        values.put(DatabaseContract.User.USER_COLUMN_USERNAME, User.getUsername());
        values.put(DatabaseContract.User.USER_COLUMN_PASSWORD, User.getPassword());
        values.put(DatabaseContract.User.USER_COLUMN_NAME, User.getName());

        values.put(DatabaseContract.User.USER_COLUMN_AGE, User.getAge());
        values.put(DatabaseContract.User.USER_COLUMN_DATEOFBIRTH, User.getBirthDate());
        values.put(DatabaseContract.User.USER_COLUMN_EMAIL, User.getEmail());

        values.put(DatabaseContract.User.USER_COLUMN_WEIGHT, User.getWeight());
        values.put(DatabaseContract.User.USER_COLUMN_HEIGHT, User.getHeight());
        values.put(DatabaseContract.User.USER_COLUMN_SENSITIVE_FOOD, User.getSensitiveFood());
        values.put(DatabaseContract.User.USER_COLUMN_HEARTRATE, User.getHeartRate());
        values.put(DatabaseContract.User.USER_COLUMN_REGISTERDATE, User.getRegisterDate());

        SQLiteDatabase = dbHelper.getWritableDatabase();
        long a = SQLiteDatabase.insert(DatabaseContract.User.USER_TABLE_NAME, null, values);

        SQLiteDatabase.close();
        Toast.makeText(context, "a = " + a, Toast.LENGTH_LONG).show();

    }

    public List<UserRecord> getAllUsers(){
        List<UserRecord> records = new ArrayList<UserRecord>();
        Cursor cursor = SQLiteDatabase.query(User.USER_TABLE_NAME, UserAllColumn , null,
                null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            UserRecord userRecord = new UserRecord();
            Toast.makeText(context, "retrieve user id = " + cursor.getString(0), Toast.LENGTH_LONG).show();

            userRecord.setID(cursor.getString(0));
            userRecord.setUsername(cursor.getString(1));
            userRecord.setPassword(cursor.getString(2));
            userRecord.setName(cursor.getString(3));

            userRecord.setAge(cursor.getInt(4));
            userRecord.setBirthDate(cursor.getString(5));

            userRecord.setEmail(cursor.getString(6));
            userRecord.setWeight(cursor.getDouble(7));
            userRecord.setHeight(cursor.getDouble(8));

            userRecord.setSensitiveFood(cursor.getString(9));
            userRecord.setHeartRate(cursor.getDouble(10));
            userRecord.setRegisterDate(cursor.getString(11));

            records.add(userRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;
    }

    public int deleteRecord(String username)
    {
        SQLiteDatabase = dbHelper.getWritableDatabase();
        String[] whereArgs ={username.toString()};
        int count = SQLiteDatabase.delete(User.USER_TABLE_NAME,User.USER_COLUMN_USERNAME + "=?",whereArgs);
        return count;
    }
}
