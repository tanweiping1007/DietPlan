package database;


import android.graphics.Bitmap;

import java.sql.Blob;

public class DietPlanPackageRecord {

    String package_id;
    String package_thumbnail;
    String package_suitable;
    String package_description;

    String package_description_day1_morning;
    String package_description_day1_evening;
    String package_description_day1_night;

    String package_description_day2_morning;
    String package_description_day2_evening;
    String package_description_day2_night;

    String package_description_day3_morning;
    String package_description_day3_evening;
    String package_description_day3_night;

    String package_description_day4_morning;
    String package_description_day4_evening;
    String package_description_day4_night;

    String package_description_day5_morning;
    String package_description_day5_evening;
    String package_description_day5_night;

    public DietPlanPackageRecord(){

    }

    public DietPlanPackageRecord(String package_id, String package_thumbnail, String package_suitable,
                                 String package_description_day1_morning, String package_description_day1_evening, String package_description_day1_night,
                                 String package_description_day2_morning, String package_description_day2_evening, String package_description_day2_night,
                                 String package_description_day3_morning, String package_description_day3_evening, String package_description_day3_night,
                                 String package_description_day4_morning, String package_description_day4_evening, String package_description_day4_night,
                                 String package_description_day5_morning, String package_description_day5_evening, String package_description_day5_night

                                 )
    {
        this.package_id = package_id;
        this.package_thumbnail = package_thumbnail;
        this.package_suitable = package_suitable;
        this.package_description_day1_morning = package_description_day1_morning;
        this.package_description_day1_evening = package_description_day1_evening;
        this.package_description_day1_night = package_description_day1_night;

        this.package_description_day2_morning = package_description_day2_morning;
        this.package_description_day2_evening =package_description_day2_evening;
        this.package_description_day2_night =package_description_day2_night;

        this.package_description_day3_morning =package_description_day3_morning;
        this.package_description_day3_evening =package_description_day3_evening;
        this.package_description_day3_night =package_description_day3_night;

        this.package_description_day4_morning =package_description_day4_morning;
        this.package_description_day4_evening =package_description_day4_evening;
        this.package_description_day4_night =package_description_day4_night;

        this.package_description_day5_morning =package_description_day5_morning;
        this.package_description_day5_evening =package_description_day5_evening;
        this.package_description_day5_night =package_description_day5_night;    }

    // package id
    public String getPackage_id() {
        return package_id;
    }
    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    // package thumbnail
    public String getPackage_thumbnail() {
        return package_thumbnail;
    }
    public void setPackage_thumbnail(String package_thumbnail) {
        this.package_thumbnail = package_thumbnail;
    }

    //package_suitable
    public String getPackage_suitable(){
        return package_suitable;
    }
    public void setPackage_suitable(String package_suitable){
        this.package_suitable = package_suitable;
    }

    //package_description
    public String getPackage_description(){
        return package_description;
    }
    public void setPackage_description(String package_description){
        this.package_description = package_description;
    }


    //package_description_day1_morning
    public String getPackage_description_day1_morning(){
        return package_description_day1_morning;
    }
    public void setPackage_description_day1_morning(String package_description_day1_morning){
        this.package_description_day1_morning = package_description_day1_morning;
    }

    //package_description_day1_evening
    public String getPackage_description_day1_evening(){
        return package_description_day1_evening;
    }
    public void setPackage_description_day1_evening(String package_description_day1_evening){
        this.package_description_day1_evening = package_description_day1_evening;
    }

    //package_description_day1_night
    public String getPackage_description_day1_night(){
        return package_description_day1_night;
    }
    public void setPackage_description_day1_night(String package_description_day1_night){
        this.package_description_day1_night = package_description_day1_night;
    }

//----

    //package_description_day2_morning
    public String getPackage_description_day2_morning(){
        return package_description_day2_morning;
    }
    public void setPackage_description_day2_morning(String package_description_day2_morning){
        this.package_description_day2_morning = package_description_day2_morning;
    }

    //package_description_day2_evening
    public String getPackage_description_day2_evening(){
        return package_description_day2_evening;
    }
    public void setPackage_description_day2_evening(String package_description_day2_evening){
        this.package_description_day2_evening = package_description_day2_evening;
    }

    //package_description_day2_night
    public String getPackage_description_day2_night(){
        return package_description_day2_night;
    }
    public void setPackage_description_day2_night(String package_description_day2_night){
        this.package_description_day2_night = package_description_day2_night;
    }


    //--- day3

    //package_description_day3_morning
    public String getPackage_description_day3_morning(){
        return package_description_day3_morning;
    }
    public void setPackage_description_day3_morning(String package_description_day3_morning){
        this.package_description_day3_morning = package_description_day3_morning;
    }

    //package_description_day3_evening
    public String getPackage_description_day3_evening(){
        return package_description_day3_evening;
    }
    public void setPackage_description_day3_evening(String package_description_day3_evening){
        this.package_description_day3_evening = package_description_day3_evening;
    }

    //package_description_day3_night
    public String getPackage_description_day3_night(){
        return package_description_day3_night;
    }
    public void setPackage_description_day3_night(String package_description_day3_night){
        this.package_description_day3_night = package_description_day3_night;
    }

    //--- day4

    //package_description_day4_morning
    public String getPackage_description_day4_morning(){
        return package_description_day4_morning;
    }
    public void setPackage_description_day4_morning(String package_description_day4_morning){
        this.package_description_day4_morning = package_description_day4_morning;
    }

    //package_description_day4_evening
    public String getPackage_description_day4_evening(){
        return package_description_day4_evening;
    }
    public void setPackage_description_day4_evening(String package_description_day4_evening){
        this.package_description_day4_evening = package_description_day4_evening;
    }

    //package_description_day4_night
    public String getPackage_description_day4_night(){
        return package_description_day4_night;
    }
    public void setPackage_description_day4_night(String package_description_day4_night){
        this.package_description_day4_night = package_description_day4_night;
    }


    //--- day5

    //package_description_day5_morning
    public String getPackage_description_day5_morning(){
        return package_description_day5_morning;
    }
    public void setPackage_description_day5_morning(String package_description_day5_morning){
        this.package_description_day5_morning = package_description_day5_morning;
    }

    //package_description_day5_evening
    public String getPackage_description_day5_evening(){
        return package_description_day5_evening;
    }
    public void setPackage_description_day5_evening(String package_description_day5_evening){
        this.package_description_day5_evening = package_description_day5_evening;
    }

    //package_description_day5_night
    public String getPackage_description_day5_night(){
        return package_description_day5_night;
    }
    public void setPackage_description_day5_night(String package_description_day5_night){
        this.package_description_day5_night = package_description_day5_night;
    }
}
