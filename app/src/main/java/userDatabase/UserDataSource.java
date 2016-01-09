package userDatabase;

import java.util.ArrayList;
import java.util.List;
import userDatabase.UserContract.User;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class UserDataSource {
    private SQLiteDatabase database;
    private UserSQLHelper dbHelper;
    private String[] allColumn = {
            User.COLUMN_NAME,
            User.COLUMN_EMAIL
    };
    public UserDataSource(Context context){
        dbHelper = new UserSQLHelper(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public void insertUser(UserRecord userRecord){
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, userRecord.getName());
        values.put(User.COLUMN_EMAIL, userRecord.getEmail());
        database = dbHelper.getWritableDatabase();
        database.insert(User.TABLE_NAME, null, values);
        database.close();
    }
    public List<UserRecord> getAllUsers(){
        List<UserRecord> records = new ArrayList<UserRecord>();
        Cursor cursor = database.query(User.TABLE_NAME, allColumn , null,
                null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            UserRecord userRecord = new UserRecord();
            userRecord.setName(cursor.getString(0));
            userRecord.setEmail(cursor.getString(1));
            records.add(userRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;
    }
}