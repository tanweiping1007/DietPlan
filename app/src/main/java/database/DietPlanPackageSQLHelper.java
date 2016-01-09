package database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import database.DietPlanPackageContract.DietPackage;

public class DietPlanPackageSQLHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dietplanpackage.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DietPackage.TABLE_NAME + "(" +
                    DietPackage.COLUMN_ID + " TEXT," +
                    DietPackage.COLUMN_THUMBNAIL + " TEXT," +
                    DietPackage.COLUMN_SUITABLE + " TEXT," +
                    //DietPackage.COLUMN_DESCRIPTION + " TEXT," +

                    DietPackage.COLUMN_DESCRIPTION_DAY1_MORNING + " TEXT," +
                    DietPackage.COLUMN_DESCRIPTION_DAY1_EVENING + " TEXT," +
                    DietPackage.COLUMN_DESCRIPTION_DAY1_NIGHT + " TEXT," +

                    DietPackage.COLUMN_DESCRIPTION_DAY2_MORNING + " TEXT," +
                    DietPackage.COLUMN_DESCRIPTION_DAY2_EVENING + " TEXT," +
                    DietPackage.COLUMN_DESCRIPTION_DAY2_NIGHT + " TEXT," +

                    DietPackage.COLUMN_DESCRIPTION_DAY3_MORNING + " TEXT," +
                    DietPackage.COLUMN_DESCRIPTION_DAY3_EVENING + " TEXT," +
                    DietPackage.COLUMN_DESCRIPTION_DAY3_NIGHT + " TEXT," +

                    DietPackage.COLUMN_DESCRIPTION_DAY4_MORNING + " TEXT," +
                    DietPackage.COLUMN_DESCRIPTION_DAY4_EVENING + " TEXT," +
                    DietPackage.COLUMN_DESCRIPTION_DAY4_NIGHT + " TEXT," +

                    DietPackage.COLUMN_DESCRIPTION_DAY5_MORNING + " TEXT," +
                    DietPackage.COLUMN_DESCRIPTION_DAY5_EVENING + " TEXT," +
                    DietPackage.COLUMN_DESCRIPTION_DAY5_NIGHT + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DietPackage.TABLE_NAME;
    public DietPlanPackageSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// This database is only a cache for online data, so its upgrade
// policy is to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
