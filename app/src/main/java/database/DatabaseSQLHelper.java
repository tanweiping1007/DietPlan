package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by STUDIO on 13/12/2015.
 */
public class DatabaseSQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "diet.db";

    private static final String SQL_CREATE_USER_TABLE =
            "CREATE TABLE " + DatabaseContract.User.USER_TABLE_NAME + "(" +
                    DatabaseContract.User.USER_COLUMN_ID + " TEXT," +
                    DatabaseContract.User.USER_COLUMN_USERNAME + " TEXT," +
                    DatabaseContract.User.USER_COLUMN_PASSWORD + " TEXT," +


                    DatabaseContract.User.USER_COLUMN_NAME + " TEXT," +
                    DatabaseContract.User.USER_COLUMN_AGE + " INT," +
                    DatabaseContract.User.USER_COLUMN_DATEOFBIRTH + " TEXT," +

                    DatabaseContract.User.USER_COLUMN_EMAIL + " TEXT," +
                    DatabaseContract.User.USER_COLUMN_WEIGHT + " DOUBLE," +
                    DatabaseContract.User.USER_COLUMN_HEIGHT + " DOUBLE," +

                    DatabaseContract.User.USER_COLUMN_SENSITIVE_FOOD + " TEXT," +
                    DatabaseContract.User.USER_COLUMN_HEARTRATE + " DOUBLE," +

                    DatabaseContract.User.USER_COLUMN_REGISTERDATE + " TEXT)";


    private static final String SQL_DELETE_USER_TABLE =
            "DROP TABLE IF EXISTS " + DatabaseContract.User.USER_TABLE_NAME;
    public DatabaseSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// This database is only a cache for online data, so its upgrade
// policy is to simply to discard the data and start over
        db.execSQL(SQL_DELETE_USER_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
