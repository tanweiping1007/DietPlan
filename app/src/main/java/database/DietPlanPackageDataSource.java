package database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import database.DietPlanPackageContract.DietPackage;

public class DietPlanPackageDataSource {

    private Context context;
    private SQLiteDatabase database;
    private DietPlanPackageSQLHelper dbHelper;
    private String[] allColumn = {
            DietPackage.COLUMN_ID,
            DietPackage.COLUMN_THUMBNAIL,
            DietPackage.COLUMN_SUITABLE,
            DietPackage.COLUMN_DESCRIPTION_DAY1_MORNING,
            DietPackage.COLUMN_DESCRIPTION_DAY1_EVENING,
            DietPackage.COLUMN_DESCRIPTION_DAY1_NIGHT,

            DietPackage.COLUMN_DESCRIPTION_DAY2_MORNING,
            DietPackage.COLUMN_DESCRIPTION_DAY2_EVENING,
            DietPackage.COLUMN_DESCRIPTION_DAY2_NIGHT,

            DietPackage.COLUMN_DESCRIPTION_DAY3_MORNING,
            DietPackage.COLUMN_DESCRIPTION_DAY3_EVENING,
            DietPackage.COLUMN_DESCRIPTION_DAY3_NIGHT,

            DietPackage.COLUMN_DESCRIPTION_DAY4_MORNING,
            DietPackage.COLUMN_DESCRIPTION_DAY4_EVENING,
            DietPackage.COLUMN_DESCRIPTION_DAY4_NIGHT,

            DietPackage.COLUMN_DESCRIPTION_DAY5_MORNING,
            DietPackage.COLUMN_DESCRIPTION_DAY5_EVENING,
            DietPackage.COLUMN_DESCRIPTION_DAY5_NIGHT

    };
    public DietPlanPackageDataSource(Context context){
        this.context = context;
        dbHelper = new DietPlanPackageSQLHelper(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public void insertData(DietPlanPackageRecord dietPlanPackageRecord){
        ContentValues values = new ContentValues();
        values.put(DietPackage.COLUMN_ID, dietPlanPackageRecord.getPackage_id());
        values.put(DietPackage.COLUMN_THUMBNAIL, dietPlanPackageRecord.getPackage_thumbnail());
        values.put(DietPackage.COLUMN_SUITABLE, dietPlanPackageRecord.getPackage_suitable());
        //values.put(DietPackage.COLUMN_DESCRIPTION, dietPlanPackageRecord.getPackage_description());

        values.put(DietPackage.COLUMN_DESCRIPTION_DAY1_MORNING, dietPlanPackageRecord.getPackage_description_day1_morning());
        values.put(DietPackage.COLUMN_DESCRIPTION_DAY1_EVENING, dietPlanPackageRecord.getPackage_description_day1_evening());
        values.put(DietPackage.COLUMN_DESCRIPTION_DAY1_NIGHT, dietPlanPackageRecord.getPackage_description_day1_night());

        values.put(DietPackage.COLUMN_DESCRIPTION_DAY2_MORNING, dietPlanPackageRecord.getPackage_description_day2_morning());
        values.put(DietPackage.COLUMN_DESCRIPTION_DAY2_EVENING, dietPlanPackageRecord.getPackage_description_day2_evening());
        values.put(DietPackage.COLUMN_DESCRIPTION_DAY2_NIGHT, dietPlanPackageRecord.getPackage_description_day2_night());

        values.put(DietPackage.COLUMN_DESCRIPTION_DAY3_MORNING, dietPlanPackageRecord.getPackage_description_day3_morning());
        values.put(DietPackage.COLUMN_DESCRIPTION_DAY3_EVENING, dietPlanPackageRecord.getPackage_description_day3_evening());
        values.put(DietPackage.COLUMN_DESCRIPTION_DAY3_NIGHT, dietPlanPackageRecord.getPackage_description_day3_night());

        values.put(DietPackage.COLUMN_DESCRIPTION_DAY4_MORNING, dietPlanPackageRecord.getPackage_description_day4_morning());
        values.put(DietPackage.COLUMN_DESCRIPTION_DAY4_EVENING, dietPlanPackageRecord.getPackage_description_day4_evening());
        values.put(DietPackage.COLUMN_DESCRIPTION_DAY4_NIGHT, dietPlanPackageRecord.getPackage_description_day4_night());

        values.put(DietPackage.COLUMN_DESCRIPTION_DAY5_MORNING, dietPlanPackageRecord.getPackage_description_day5_morning());
        values.put(DietPackage.COLUMN_DESCRIPTION_DAY5_EVENING, dietPlanPackageRecord.getPackage_description_day5_evening());
        values.put(DietPackage.COLUMN_DESCRIPTION_DAY5_NIGHT, dietPlanPackageRecord.getPackage_description_day5_night());

        database = dbHelper.getWritableDatabase();
        database.insert(DietPackage.TABLE_NAME, null, values);
        database.close();
    }
    public ArrayList<DietPlanPackageRecord> getAllData(){
        ArrayList<DietPlanPackageRecord> records = new ArrayList<DietPlanPackageRecord>();
        Cursor cursor = database.query(DietPackage.TABLE_NAME, allColumn , null,
                null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DietPlanPackageRecord dietPlanPackageRecord = new DietPlanPackageRecord();
            dietPlanPackageRecord.setPackage_id(cursor.getString(0));
            dietPlanPackageRecord.setPackage_thumbnail(cursor.getString(1));
            dietPlanPackageRecord.setPackage_suitable(cursor.getString(2));

            dietPlanPackageRecord.setPackage_description_day1_morning(cursor.getString(3));
            dietPlanPackageRecord.setPackage_description_day1_evening(cursor.getString(4));
            dietPlanPackageRecord.setPackage_description_day1_night(cursor.getString(5));

            dietPlanPackageRecord.setPackage_description_day2_morning(cursor.getString(6));
            dietPlanPackageRecord.setPackage_description_day2_evening(cursor.getString(7));
            dietPlanPackageRecord.setPackage_description_day2_night(cursor.getString(8));

            dietPlanPackageRecord.setPackage_description_day3_morning(cursor.getString(9));
            dietPlanPackageRecord.setPackage_description_day3_evening(cursor.getString(10));
            dietPlanPackageRecord.setPackage_description_day3_night(cursor.getString(11));

            dietPlanPackageRecord.setPackage_description_day4_morning(cursor.getString(12));
            dietPlanPackageRecord.setPackage_description_day4_evening(cursor.getString(13));
            dietPlanPackageRecord.setPackage_description_day4_night(cursor.getString(14));

            dietPlanPackageRecord.setPackage_description_day5_morning(cursor.getString(15));
            dietPlanPackageRecord.setPackage_description_day5_evening(cursor.getString(16));
            dietPlanPackageRecord.setPackage_description_day5_night(cursor.getString(17));

            records.add(dietPlanPackageRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;
    }



}


       /*

Double userLoanAmount;
Double userInterestRate;
int userPeriodYear;
private SQLiteDatabase database;
private DietPlanPackageSQLHelper dbHelper;
private String[] allColumn = {
        DietPackage.COLUMN_ID,
        DietPackage.COLUMN_SUITABLE,
        DietPackage.COLUMN_DESCRIPTION
};

    public DietPlanPackageDataSource(Context context){
        dbHelper = new DietPlanPackageSQLHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public void insertUser(DietPlanPackageRecord loanRecord){
        ContentValues values = new ContentValues();
        values.put(DietPackage.COLUMN_ID, loanRecord.getPackage_id());
        values.put(DietPackage.COLUMN_SUITABLE, loanRecord.getPackage_suitable());
        values.put(DietPackage.COLUMN_DESCRIPTION, loanRecord.getPackage_description());


        database = dbHelper.getWritableDatabase();
        database.insert(DietPackage.TABLE_NAME, null, values);
        database.close();
    }
    public List<DietPlanPackageRecord> getAllUsers(){
        List<DietPlanPackageRecord> records = new ArrayList<DietPlanPackageRecord>();
        Cursor cursor = database.query(DietPackage.TABLE_NAME, allColumn , null,
                null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DietPlanPackageRecord loanRecord = new DietPlanPackageRecord();
            loanRecord.setPackage_id(cursor.getString(0));
            loanRecord.setPackage_suitable(cursor.getString(1));
            loanRecord.setPackage_description(cursor.getString(2));

            records.add(loanRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;
    }


    public int deleteRecord(String date)
    {
        database = dbHelper.getWritableDatabase();
        String[] whereArgs ={date.toString()};
        int count = database.delete(DietPackage.TABLE_NAME,DietPackage.COLUMN_ID + "=?",whereArgs);
        return count;
    }
*/


